import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from '@angular/common/http';
import {Observable, throwError, EMPTY} from 'rxjs';
import {AuthService} from './auth.service';
import {catchError} from 'rxjs/operators';
import Swal from 'sweetalert2';
import {URL_BACKEND} from '../../config/config';

@Injectable({
  providedIn: 'root'
})

export class MaterialService  {

  private urlEndPoint =  URL_BACKEND + '/api/material';

  constructor(private http: HttpClient , private authService: AuthService) {}

  /**
   * Metodo que permite recuperar los materiales de un curso
   * @param cursoId valor entero del id del curso
   * retorna un observable con el resultado y las excepciones en cada caso
   */
  obtenerMaterialesCurso(cursoId: number): Observable<any> {
    // se establece el parametro
    const paramCursoId = '?cursoId=' + cursoId;
    // luego se construye la subruta
    const subRutaMateriales = this.urlEndPoint + '/materiales_grupo' + paramCursoId;
    // cabeceras de autorizacion
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    // finalmente se hace la petición
    return this.http.get<any>(subRutaMateriales, {headers: httpHeaders}).pipe(
      catchError( err => {
        // En caso de error se muestra desde el servicio
        Swal.fire({
          title: 'Error al obtener materiales del curso',
          text: err.error.error,
          type: 'error',
          confirmButtonText: 'Aceptar'
        });
        return throwError(err);
      })
    );
  }

  /**
   * Metodo que hace comunicación con el backend para crear un archivo
   * @param nombreMaterial Nombre del material
   * @param descripcionMaterial Descripción del material
   * @param sbmId Identificador entero de la subsección de material
   * @param archivo Archivo seleccionado para el material
   */
  crearMaterial( nombreMaterial: string, descripcionMaterial: string, sbmId: number, archivo: File): Observable<any> {
    // subruta para acceder al backend
    const subRutaCrearMaterial = this.urlEndPoint + '/materiales_grupo/upload';
    // datos para el formulario
    const formData = new FormData();
    formData.append('archivo', archivo);
    formData.append('nombreMaterial', nombreMaterial);
    formData.append('descripcion', descripcionMaterial);
    formData.append('sbmId', sbmId.toString());
    // cabeceras de autorizacion
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    // para control del progreso
    const req = new HttpRequest('POST', subRutaCrearMaterial, formData, {
      reportProgress: true, headers: httpHeaders
    });
    // finalmente se retorna el resultado
    return this.http.request(req).pipe(
      catchError ( err => {
        Swal.fire({
          title: 'Ocurrió un error al crear el material',
          text: err.error.error,
          type: 'error',
          confirmButtonText: 'Aceptar',
          backdrop: 'transparent'
        });
        // Ya no se lanza más la excepcion
        return throwError(err);
      })
    );
  }

}
