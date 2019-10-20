import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Usuario} from '../schema/Usuario';
import {objectLiteralExpression} from 'codelyzer/util/astQuery';
import {UsuarioPK} from '../schema/UsuarioPK';
import {URL_BACKEND} from '../../config/config';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _usuario: Usuario;
  private _accessToken: string;
  private _PERFIL_INSTITUCION_LLAVE = 'perfilesInstituciones';
  private _PERFIL_INSTITUCION_PERMISO_LLAVE = 'perfilesInstitucionesPermisos';
  private _USUARIO_LLAVE = 'usuario';

  constructor(private http: HttpClient) { }

  /**
   *  Método get que permite obtener un usuario depende de donde esté
   */
  public get usuario(): Usuario {
    if (this._usuario !== null && this._usuario !== undefined ) {
      return this._usuario;
    } else if ( (this._usuario == null || this._usuario == undefined) && sessionStorage.getItem('usuario') != null) {
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }
    return new Usuario();
  }

  public get accessToken(): string {
    if (this._accessToken !== null  && this._accessToken !== undefined) {
      return this._accessToken;
    } else if ((this._accessToken == null || this._accessToken === undefined ) && sessionStorage.getItem('accessToken') != null) {
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
    const urlEndpoint = URL_BACKEND + '/oauth/token';
    const credenciales = btoa('peai_angulaapp' + ':' + '12345');
    // Se definen los encabezados para el tipo de autorización del protocolo
    const httpHeaders = new HttpHeaders(
      {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + credenciales
      } );

    // Se crean los parametros que van en los header de la petición
    const  params = new URLSearchParams();
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
    const payLoad = this.obtenerDatosToken(accessToken);
    this._usuario = new Usuario();
    this._usuario.nombre = payLoad.nombre;
    this._usuario.apellidos = payLoad.apellidos;
    this._usuario.usuarioPK = new UsuarioPK();
    this._usuario.usuarioPK.nombreUsuario = payLoad.user_name;
    sessionStorage.setItem(this._USUARIO_LLAVE, JSON.stringify(this._usuario));
  }

  /**
   * Método que permite guardar el token en el sessionStorage para usarlo cuando sea necesario
   * @param accessToken Token de acceso del frontend a recursos backend
   */
  guardarToken( accessToken: string) {
    this._accessToken = accessToken;
    sessionStorage.setItem('accessToken', this._accessToken);
  }

  /**
   * Método que permite obtener los datos importantes del session storage
   * @param accessToken Token de acceso del frontend a recursos backend
   */
  obtenerDatosToken(accessToken: string): any {
    if (accessToken !== null) {
      // Para obtener la información extra del payload
      return JSON.parse(atob(accessToken.split('.')[1]));
    }
    return null;
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
    sessionStorage.setItem( this._PERFIL_INSTITUCION_LLAVE, perfilesInstituciones.toString());
  }

  /**
   * Método que permite obtener una lista de objetos con dos atributos:
   * perfil: el nombre del perfil.
   * instituciones: un array con los nombres de instituciones en cada perfil.
   * Retorna nulo en caso de que no haya nada en el sessionStorage
   */
  obtenerPerfilesInstituciones(): any {
    if ( sessionStorage.getItem(this._PERFIL_INSTITUCION_LLAVE)  == null ) {
      return null;
    }
    return JSON.parse( '[' +  sessionStorage.getItem(this._PERFIL_INSTITUCION_LLAVE) + ']' );
  }

  /**
   * Método que almacena los permisos de los perfiles por institucion en el sessionStorage para consultarlos cuando
   * sea necesario
   * @param pip Objeto recuperado del token para ser formateado y almacenado en el sessionStorage
   */
  guardarPerfilesInstitucionesPermisosUsuario(pip: any): void {
    const perfilesInstitucionesPermisos = [];
    const llaves = Object.keys(pip);
    llaves.map(
      valor => {
        const temp = JSON.stringify({ perfilInstitucion: valor.replace(/-/g, ' '), permisos: pip[valor]});
        perfilesInstitucionesPermisos.push(temp);
      }
    );
    sessionStorage.setItem(this._PERFIL_INSTITUCION_PERMISO_LLAVE, perfilesInstitucionesPermisos.toString());
  }

  /**
   * Método que devuelve una lista de objetos que tiene como atributos lo siguiente
   * perfilInstitucion: El nombre del perfil  + * + nombre de la institucion
   * permisos: una lista de nombres de permisos
   * Devuelve nulo si no se encuentra en el sessionStorage.
   */
  obtenerPerfilesInstitucionesPermisosUsuario(): any {
    if ( sessionStorage.getItem(this._PERFIL_INSTITUCION_PERMISO_LLAVE) == null) {
      return null;
    }
    return JSON.parse( '[' + sessionStorage.getItem(this._PERFIL_INSTITUCION_PERMISO_LLAVE) + ']');
  }

  /**
   * Permite verificar si hay un usuario logeado o no
   * Retorna verdadero o falso según corresponda
   */
  usuarioEstaLogeado(): boolean {
    const datos = this.obtenerDatosToken(this.accessToken);
    if ( datos != null && datos.user_name && datos.user_name.length) {
      return true;
    }
    return false;
  }

  /**
   * Método que permite hacer el cierre de sesión de los datos de la aplicación.
   */
  cerrarSesion(): void {
    this._accessToken = null;
    this._usuario = null;
    sessionStorage.clear();
  }

  /**
   * Método que permite verificar si un usuario tiene un perfil en una institucion específica
   * @param perfil Constante string con el nombre del perfil
   * @param nombreInstitucion nombre de la institucion
   * Retorna verdadero o falso si tiene o no ese perfil en esa institucion
   */
  tienePerfilEnInstitucion(perfil: string, nombreInstitucion: string): boolean {
    let resultado = false;
    const perfilesPorInstitucion = this.obtenerPerfilesInstituciones();
    if ( perfilesPorInstitucion == null || perfilesPorInstitucion === undefined ) { return false; }
    perfilesPorInstitucion.map(
      (objetoPerfilInstitucion: any) => {
        if ((objetoPerfilInstitucion.perfil as string) === perfil) {
          objetoPerfilInstitucion.instituciones.map(
            (ni: string) => { if (ni === nombreInstitucion ) { resultado = true; } }
          );
        }
      }
    );
    return resultado;
  }

  /**
   * método que permite verificar si existe la combinación permiso-perfil-institucion
   * @param permiso nombre del permiso a verificar
   * @param perfil nombre del perfil (Constante) para verificar
   * @param nombreInstitucion nombre de la institucion
   * Retorna verdadero o falso segun corresponda
   */
  tienePermisoEnPerfilInstitucion(permiso: number, perfil: string, nombreInstitucion: string ): boolean {
    let resultado = false;
    const permisosPerfilesInstitucion = this.obtenerPerfilesInstitucionesPermisosUsuario();
    if ( permisosPerfilesInstitucion === null || permisosPerfilesInstitucion === undefined ) {
      return false;
    } else {
      const llaveTemporal = nombreInstitucion + '*' + perfil;
      permisosPerfilesInstitucion.map(
        (pip: any) => {
          if ( pip.perfilInstitucion === llaveTemporal ) {
              pip.permisos.map(
                (p: string) => { if ( p === permiso.toString() ) { resultado = true; return; } }
              );
          }
      }
      );
    }
    return resultado;
  }

  /**
   * Método que permite verificar si se tiene un permiso en algún perfil y en alguna institucion en general
   * @param permisoId Id del permiso. Valor obtenido de una de las constantes
   * Retorna verdadero o falso si se tiene o no ese permito
   */
  validarTienePermisoEnAlgunPerfil(permisoId: number ): boolean {
    let resultado = false;
    const perfilesInstitciones = this.obtenerPerfilesInstituciones();
    perfilesInstitciones.map(
      pi => {
        const idPerfil = pi.perfil as string;
        pi.instituciones.map(
          nombreInstitucion => {
            if (this.tienePermisoEnPerfilInstitucion(permisoId,
              nombreInstitucion, idPerfil)) {
              resultado = true;
              return;
            }
          }
        );
      }
    );
    return resultado;
  }

  /**
   * Método que permite verificar si se tiene un permiso en algún perfil y de una institucion especifica
   * @param permisoId Id del permiso. Valor obtenido de una de las constantes
   * Retorna verdadero o falso si se tiene o no ese permito
   */
  validarTienePermisoEnAlgunPerfilDeInstitucion(permisoId: number, institucionId: string): boolean {
    let resultado = false;
    const perfilesInstitciones = this.obtenerPerfilesInstituciones();
    perfilesInstitciones.map(
      pi => {
        const idPerfil = pi.perfil as string;
        pi.instituciones.map(
          nombreInstitucion => {
            if (nombreInstitucion === institucionId && this.tienePermisoEnPerfilInstitucion(permisoId,
              nombreInstitucion, idPerfil)) {
              resultado = true;
              return;
            }
          }
        );
      }
    );
    return resultado;
  }
}



