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

}
