import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';
import {UsuarioMaterialComenta} from '../schema/UsuarioMaterialComenta';

@Injectable({
  providedIn: 'root'
})

export class ForoService {

  constructor(private authService: AuthService, private http: HttpClient) { }

  readonly urlEndpoint = 'http://localhost:8080/api/foro';

  /**
   * Método que agrega un comentario al foro
   * @param usuarioMaterialComenta es el comentario en forma de entidad
   */
  setComentarioMaterial(usuarioMaterialComenta: UsuarioMaterialComenta): Observable<any> {
    // Ruta para hacer el post del comentario
    const rutaComentarMaterial = '/comentar_material';

    // URL completo
    const urlConsulta = `${this.urlEndpoint}${rutaComentarMaterial}`;

    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken
      } );
    return this.http.post<any>(urlConsulta, usuarioMaterialComenta, {headers: httpHeaders});
  }

  /**
   * Método que obtiene los comentarios de un material especifico
   * @param idMaterial es el ID del material
   * @param idSubSeccion es el ID de la subsección a la que pertenece
   */
  getComentariosMaterial(idMaterial: string, idSubSeccion: number): Observable<any> {
    // ruta pra obtener todos los comentarios
    const rutaComentariosMaterial = '/obtener_comentarios?';

    // Parametros de consulta
    const parametro1Consulta = 'idMaterial=';
    const parametro2Consulta = 'idSubSeccion=';

    // Se hace el url de la consulta completa
    // tslint:disable-next-line:max-line-length
    const urlConsulta = `${this.urlEndpoint}${rutaComentariosMaterial}${parametro1Consulta}${idMaterial}&${parametro2Consulta}${idSubSeccion}`;

    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken
      } );
    return this.http.get<any>(urlConsulta, {headers: httpHeaders});
  }
}
