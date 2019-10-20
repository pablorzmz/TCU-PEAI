import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';
import {URL_BACKEND} from '../../config/config';

@Injectable({
  providedIn: 'root'
})
export class InstitucionService {
  private urlEndPoint = URL_BACKEND + '/api/instituciones';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getInstituciones(): Observable<any> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    const urlAdd = '/obtener_instituciones';
    return this.http.get(this.urlEndPoint + urlAdd, {headers: httpHeaders});
  }

  getInstituciones2(page: number): Observable<any> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    const urlAdd = '/obtener_instituciones/page/';
    return this.http.get(this.urlEndPoint + urlAdd + page, {headers: httpHeaders});
  }

}
