import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthService} from './auth.service';
import {Observable} from 'rxjs';
import {UsuarioGrupoInscritoPK} from '../schema/UsuarioGrupoInscritoPK';
import {GrupoPK} from '../schema/GrupoPK';

@Injectable({
  providedIn: 'root'
})
export class UsuarioGrupoInscritoService {
  private urlEndPoint = 'http://localhost:8080/api/usr_grp_incs/';

  constructor(private http: HttpClient,
              private authService: AuthService) { }

  getEstudiantesDeGrupo(cursoId: number, numGrupo: number, periodoT: string): Observable<any> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    let urlAdd = 'estudiantes_de_grupo';
    urlAdd += '?cursoId=' + cursoId;
    urlAdd += '&numGrupo=' + numGrupo;
    urlAdd += '&periodoT=' + periodoT;
    return this.http.get(this.urlEndPoint + urlAdd, {headers: httpHeaders});
  }

  agregarEstudianteAGrupo(usuarioGrupoInscritoPK: UsuarioGrupoInscritoPK, ): Observable<any> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    const urlAdd = 'insertar_estudiante_grupo';
    return this.http.post(this.urlEndPoint + urlAdd, usuarioGrupoInscritoPK, {headers: httpHeaders});
  }

  eliminarEstudianteDeGrupo(grupoPK: GrupoPK, nombreU: string): Observable<any> {
    const httpHeaders = new HttpHeaders(
      {
        Authorization: 'Bearer' + this.authService.accessToken
      });
    let urlAdd = 'eliminar_estudiante_grupo';
    urlAdd += '?idCurso=' + grupoPK.curso;
    urlAdd += '&numGrupo=' + grupoPK.numero;
    urlAdd += '&periodoT=' + grupoPK.periodoTiempo;
    urlAdd += '&nombreUsuario=' + nombreU;
    return this.http.delete(this.urlEndPoint + urlAdd, {headers: httpHeaders});
  }
}
