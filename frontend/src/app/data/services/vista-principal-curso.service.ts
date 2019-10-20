import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {AuthService} from './auth.service';
import {catchError, map} from 'rxjs/operators';
import Swal from 'sweetalert2';
import {URL_BACKEND} from '../../config/config';

@Injectable({
  providedIn: 'root'
})

export class VistaPrincipalCursoService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  readonly urlEndpoint =  URL_BACKEND + '/api/vista_principal_curso';

  /**
   * Método principal del servicio para obtener la información básica del curso
   * @param idCurso Valor entero id del curso
   * @param nombreUsuario Nombre del usuario que se obtiene de la sesión.
   */
  getInformacionBasicaCurso(idCurso: number): Observable<any> {
    const subRutaInformacionCurso = '/informacion_curso_vista_principal';
    const parametroIdCurso = '?id=';
    const parametroNombreUsuario = '&nombreUsuario=';
    const nombreUsuario = this.authService.usuario.usuarioPK.nombreUsuario;
    const urlConsulta = `${this.urlEndpoint}${subRutaInformacionCurso}${parametroIdCurso}
          ${idCurso}${parametroNombreUsuario}${nombreUsuario}`;
    // Se definen los encabezados para el tipo de autorización del protocolo
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken
      } );
    // Finalmente se realiza la petición
    return this.http.get<any>(urlConsulta, {headers: httpHeaders}).pipe(
      map(data => data),
      catchError( err => {
        Swal.fire('Recurso no disponible', err.error.error, 'error');
        return throwError(err);
      })
    );
  }
}
