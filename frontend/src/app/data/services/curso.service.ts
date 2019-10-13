import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';
import {Curso} from "../schema/Curso";

@Injectable({
  providedIn: 'root'
})

export class CursoService {
  private urlEndPoint = 'http://localhost:8080/api/cursos';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getCursos(page: number): Observable<any> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    const urlAdd = '/listado_cursos/page/';
    return this.http.get(this.urlEndPoint + urlAdd + page, {headers: httpHeaders});
  }

  getCursosDeArea(idArea: number): Observable<any> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    const urlAdd = '/cursos_por_area/';
    return this.http.get(this.urlEndPoint + urlAdd + idArea, {headers: httpHeaders});
  }

  createCurso(curso: Curso, idArea: number, nombreUsuario: string): Observable<Curso> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken,
        'Content-Type': 'application/json'
      });
    const urlAdd = '/crear_curso?idArea=' + idArea + '&nombreUsuario=' + nombreUsuario;
    return this.http.post<Curso>(this.urlEndPoint + urlAdd, curso, {headers: httpHeaders});
  }
}
