import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthService} from './auth.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InstitucionPerfilUsuarioService {
  private urlEndPoint = 'http://localhost:8080/api/ins_perf_usr/';

  constructor(private http: HttpClient,
              private authService: AuthService) { }

  getEstudiantesDeInstitucion(nombreInst: string, cursoId: number): Observable<any> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    let urlAdd = 'estudiantes_de_institucion';
    urlAdd += '?nombreInst=' + nombreInst;
    urlAdd += '&idCurso=' + cursoId;
    return this.http.get(this.urlEndPoint + urlAdd, {headers: httpHeaders});
  }
}
