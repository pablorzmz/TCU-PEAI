import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class InstitucionService {
  private urlEndPoint = 'http://localhost:8080/api/instituciones';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getInstituciones2(page: number): Observable<any> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    const urlAdd = '/obtener_instituciones/page/';
    return this.http.get(this.urlEndPoint + urlAdd + page, {headers: httpHeaders});
  }

}
