import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';
import {URL_BACKEND} from '../../config/config';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  constructor(private authService: AuthService, private http: HttpClient) { }

  readonly urlEndpoint =  URL_BACKEND + '/api/grupos';

  getGruposCurso(idCurso: number): Observable<any> {
    const rutaConsultarGrupos = '/listar_grupos_de_curso?';
    const parametro1Consulta = 'idCurso=';
    const parametro2Consulta = 'nombreUsuario=';
    const idUsuario = this.authService.usuario.usuarioPK.nombreUsuario;
    const urlConsulta = `${this.urlEndpoint}${rutaConsultarGrupos}${parametro1Consulta}${idCurso}&${parametro2Consulta}${idUsuario}`;
    // Se definen los encabezados para el tipo de autorización del protocolo
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken
      } );
    // Finalmente se realiza la petición
    return this.http.get<any>(urlConsulta, {headers: httpHeaders});
  }

  setGruposCurso(idCurso: number, numero: number, periodoTiempo: string, nombreUsuario: string): Observable<any> {
      const rutaConsultarGrupos = '/crear_grupo_de_curso?';
      // Se hace un set de los parametros a enviar
      const parametro1Consulta = 'idCurso=';
      const parametro2Consulta = 'numero=';
      const parametro3Consulta = 'periodoTiempo=';
      const parametro4Consulta = 'nombreUsuario=';
      // Se define el URL completo de la consulta
      // tslint:disable-next-line:max-line-length
      const urlConsulta = `${this.urlEndpoint}${rutaConsultarGrupos}${parametro1Consulta}${idCurso}&${parametro2Consulta}${numero}&${parametro3Consulta}${periodoTiempo}&${parametro4Consulta}${nombreUsuario}`;
      // Se definen los encabezados para el tipo de autorización del protocolo
      const httpHeaders = new HttpHeaders(
        {
          Authorization: 'Bearer ' + this.authService.accessToken
        } );
      // Finalmente se realiza la petición
      return this.http.post<any>(urlConsulta, {}, {headers: httpHeaders});
    }
}
