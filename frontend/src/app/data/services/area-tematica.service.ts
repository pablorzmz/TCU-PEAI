import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AreaTematicaService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  readonly urlEndpoint = 'http://localhost:8080/api/areas_tematicas';

  getAreasTematicasInstitucion(nombreInstitucion: string): Observable<any>  {
    const rutaConsultarAreasTematicas = '/listar_areas_tematicas_de_institucion';
    const parametroConsulta = '?nombre=';
    const urlConsulta = `${this.urlEndpoint}${rutaConsultarAreasTematicas}${parametroConsulta}${nombreInstitucion}`;
    // Se definen los encabezados para el tipo de autorización del protocolo
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken
      } );
    // Finalmente se realiza la petición
    return this.http.get<any>(urlConsulta, {headers: httpHeaders});
  }

  getAreasTematicas(): Observable<any>  {
    const rutaConsultarAreasTematicas = '/listar_areas_tematicas';
    const urlConsulta = `${this.urlEndpoint}${rutaConsultarAreasTematicas}`;
    // Se definen los encabezados para el tipo de autorización del protocolo
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer ' + this.authService.accessToken
      } );
    // Finalmente se realiza la petición
    return this.http.get<any>(urlConsulta, {headers: httpHeaders});
  }

}
