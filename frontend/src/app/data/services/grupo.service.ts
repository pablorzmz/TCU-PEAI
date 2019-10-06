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

  getGruposCurso(idCurso: number): Observable<any> {
    const rutaConsultarGrupos = '/listar_grupos_de_curso';
    const parametroConsulta = '?idCurso=';
    const urlConsulta = `${this.urlEndpoint}${rutaConsultarGrupos}${parametroConsulta}${idCurso}`;
    // Se definen los encabezados para el tipo de autorización del protocolo
    const httpHeaders = new HttpHeaders(
      {
        'Authorization': 'Bearer ' + this.authService.accessToken
      } );
    // Finalmente se realiza la petición
    return this.http.get<any>(urlConsulta, {headers: httpHeaders});
  }

}
