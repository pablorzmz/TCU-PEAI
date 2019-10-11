import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  constructor(private authService: AuthService, private http: HttpClient) { }

  readonly urlEndpoint = 'http://localhost:8080/api/grupos';

  getGruposCurso(idCurso: bigint): Observable<any> {
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

}
