import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Usuario} from '../schema/Usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _usuario: Usuario;
  private _accessToken: string;

  constructor(private http: HttpClient) { }

  /**
   *  Método get que permite obtener un usuario depende de donde esté
   */
  public get usuario(): Usuario {
    if (this._usuario !== null ) {
      return this._usuario;
    } else if (this._usuario == null && sessionStorage.getItem('usuario') != null){
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }
    return new Usuario();
  }

  public get accessToken(): string {
    if (this._accessToken !== null ) {
      return this._accessToken;
    } else if (this._accessToken == null && sessionStorage.getItem('accessToken') != null){
      this._accessToken = sessionStorage.getItem('accessToken');
      return this._accessToken;
    }
    return null;
  }

  /**
   * Método que realiza la autorización en sprint security como las pruebas hechoas en postman
   * @param usuario Usuario de la interfaz de login para obtener las credenciales
   */
  login(usuario: Usuario): Observable<any> {
    // La dirección del enlace para la autorización con sprint security de clientes frontend
    const urlEndpoint = 'http://localhost:8080/oauth/token';
    const credenciales = btoa('peai_angulaapp' + ':' + '12345');
    // Se definen los encabezados para el tipo de autorización del protocolo
    const httpHeaders = new HttpHeaders(
      {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + credenciales
      } );

    // Se crean los parametros que van en los header de la petición
    let params = new URLSearchParams();
    params.set('grant_type', 'password');
    params.set('username', usuario.usuarioPK.nombreUsuario);
    params.set('password', usuario.salt);

    // Finalmente se realiza la petición
    return this.http.post<any>(urlEndpoint, params.toString(), {headers: httpHeaders});
  }

  /**
   * Método que permite guardar la información del usuario y crear uno nuevo en el frontend con lo necesario
   * para realizar las validaciones
   * @param accessToken tokend de accesso de jwt
   */
  guardarUsuario(accessToken: string) {
    let payLoad= this.obtenerDatosToken(accessToken);
    this._usuario = new Usuario();
    this._usuario.nombre = payLoad.nombre;
    this._usuario.apellidos = payLoad.apellidos;
    this._usuario.usuarioPK.nombreUsuario = payLoad.user_name;
    sessionStorage.setItem('usuario', JSON.stringify(this._usuario));
  }

  /**
   * Método que permite guardar el token en el sessionStorage para usarlo cuando sea necesario
   * @param accessToken
   */
  guardarToken( accessToken: string) {
    this._accessToken = accessToken;
    sessionStorage.setItem('accessToken', this._accessToken);
  }

  /**
   * Método que permite obtener los datos importantes del session storage
   * @param accessToken
   */
  obtenerDatosToken(accessToken: string): any {
    if (accessToken !== null) {
      // Para obtener la información extra del payload
      return JSON.parse(atob(accessToken.split('.')[1]));
    }
    return null;
  }


  /**
   * Método que permite guardar una lista de objetos en la sesión  sobre los permisos que tienen
   * cada perfil del usuario en la institución específica
   * @param pip Objeto del token permiso institucion perfil
   */
  guardarPerfilesInstitucionesPermisoUsuario( pip: any): void {

  }

  /**
   * Método que guardar las instituciones por perfil del usuario a través del token jwt
   * @param pi perfilesInstituciones obtenidos del jwt
   */
  guardarPerfilesInstitucionUsuario(pi: any): void {
    const perfilesInstituciones = [];
    const llaves = Object.keys(pi);
    llaves.map(
      valor => {
        const temp = JSON.stringify({ perfil: valor, instituciones: pi[valor]});
        perfilesInstituciones.push(temp);
      }
    );
    sessionStorage.setItem('perfilesInstituciones', perfilesInstituciones.toString());
    console.log( JSON.parse('[' + sessionStorage.getItem('perfilesInstituciones') + ']') );
  }
}
