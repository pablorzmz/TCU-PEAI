import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {AuthService} from './auth.service';
import {AreaTematica} from '../schema/AreaTematica';

@Injectable({
  providedIn: 'root'
})
export class InstitucionService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  readonly urlEndpoint = 'http://localhost:8080/api/instituciones';

  getAreasTematicas(nombreInstitucion: string): Observable<any>  {
    const urlConsulta = `${this.urlEndpoint}/listar_areas_tematicas?nombre=${nombreInstitucion}`;
    // Se definen los encabezados para el tipo de autorización del protocolo
    const httpHeaders = new HttpHeaders(
      {
        'Authorization': 'Bearer ' + this.authService.accessToken
      } );
    // Finalmente se realiza la petición
    return this.http.get<any>(urlConsulta, {headers: httpHeaders});
  }

}
