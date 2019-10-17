import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {AuthService} from './auth.service';
import {catchError} from 'rxjs/operators';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class MaterialService {

  private urlEndPoint = 'http://localhost:8080/api/material';

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

}
