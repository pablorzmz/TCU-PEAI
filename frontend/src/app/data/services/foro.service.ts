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

  setComentarioMaterial(usuarioMaterialComenta: UsuarioMaterialComenta): Observable<any> {
    const rutaComentarMaterial = '/comentar_material';
    const urlConsulta = `${this.urlEndpoint}${rutaComentarMaterial}`;

    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken
      } );
    return this.http.post<any>(urlConsulta, usuarioMaterialComenta, {headers: httpHeaders});
  }
}
