import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {AuthService} from './auth.service';
import {catchError, map} from 'rxjs/operators';
import Swal from 'sweetalert2';
import {SubseccionMaterial} from '../schema/SubseccionMaterial';

@Injectable({
  providedIn: 'root'
})

export class SubseccionMaterialService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  readonly urlEndpoint = 'http://localhost:8080/api/subseccion_material';

  /**
   * Metodo del servicio que permite hacer un post y crear una nueva subsección de material en un grupo
   * @param sbm La subsección material valida con la información
   */
  crearNuevaSubseccionMaterial(sbm: SubseccionMaterial): Observable<any> {
    // sub ruta para la creación de la subsección de material
    const subRutaCrearSBM =  this.urlEndpoint + '/crear';
    // Se definen las cabeceras
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken,
        'Content-Type': 'application/json'
      } );
    // se retorna lo que se crea o bien, se muestra el error
    return this.http.post(subRutaCrearSBM, sbm, {headers: httpHeaders}).pipe(
      // servicio muestra el error
      catchError(e => {
        // error de autorización que aún no se está validando
        if (e.status === 400) {
          return throwError(e) ;
        }
        // Se dispara con un sweet alert
        Swal.fire('No se pudo crear la subsección de material', e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  /**
   * Método que permite comunicar el acción de borrar ua subsección de material al backend
   * @param sbm Subsección de material a eliminar
   * retorna un reponse con errores o mensaje de exito.
   */
  eliminarSubseccionMaterial(sbm: SubseccionMaterial): Observable<any> {
    // se crea el parametro
    const paramId = '?id=' + sbm.id;
    // se crea la subruta para eliminar
    const subRutaEliminarSBM = this.urlEndpoint + '/eliminar' + paramId;
    // Se crean las cabeceras
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken,
      } );
    // se hace el delete y se maneja la excepción
    return this.http.delete<any>(subRutaEliminarSBM,{headers: httpHeaders}).pipe(
      //  En caso de error, lo muestra el servicio
      catchError( err => {
        Swal.fire({
          title: 'Error al eliminar subsección de material',
          text: err.error.error,
          type: 'error',
          confirmButtonText: 'Aceptar'
        });
        return throwError(err);
      })
    );
  }

  /**
   * Método para actualizar una sbm, comunicacion con le backend
   * @param sbmEditado sbm para hacer el put en backend
   * retorna la respuesta con o sin el error.
   */
  actualizarSubseccionMaterial(sbmEditado: SubseccionMaterial): Observable<any> {
    // parametro
    const paramIdSBM = '?id=' + sbmEditado.id;
    // subruta
    const subRutaActualizar = this.urlEndpoint + '/actualizar' + paramIdSBM;
    // se crean los encabezados
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken,
        'Content-Type': 'application/json'
      } );
    // se hace la solicitud
    return this.http.put<any>(subRutaActualizar, sbmEditado, {headers: httpHeaders }).pipe(
      catchError (e => {
        // En caso de error, se maneja desde acá
        Swal.fire('Error al actualizar subsección material', e.error.error, 'error');
        return throwError(e);
      })
    );
  }

}
