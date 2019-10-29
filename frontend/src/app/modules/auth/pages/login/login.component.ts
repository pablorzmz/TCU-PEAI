import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import {Usuario} from '../../../../data/schema/Usuario';
import {AuthService} from '../../../../data/services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: Usuario;
  bloqueoBtnIS: boolean;

  private  RUTA_REDIRECCION = '/instituciones';

  constructor(private  authService: AuthService,
              private router: Router) {
    this.usuario = new Usuario();
    this.bloqueoBtnIS = false;
  }

  ngOnInit() {
    // En caso de haber iniciado sesión, simplemente se redirige a la dirección por defecto.
    if (this.authService.usuarioEstaLogeado()) {
      this.router.navigate([this.RUTA_REDIRECCION]);
    }
  }

  mensajeRegistro(): void {
    swal.fire('Estamos trabajando en esta funcionalidad',
      'El formulario de registro aún no se encuentra disponible',
      'info');
    }

    login(): void {
      this.bloqueoBtnIS = true;
      // Caso en que los campos del formulario sean vacíos
      if (this.usuario.usuarioPK.nombreUsuario == null || this.usuario.salt == null || this.usuario.usuarioPK.nombreUsuario === ''
        || this.usuario.salt === '') {
        swal.fire('Datos de inicio de sesión incorrectos',
          'La información de usuario no puede ser vacía.',
          'error');
        return;
      }
      //  Se llama al servicio y se suscribe al resultado
      this.authService.login(this.usuario).subscribe(
        respuesta => {
          // Se guardan el usuario y el token, perfiles, permisos e instituciones
          this.authService.guardarUsuario(respuesta.access_token);
          this.authService.guardarToken(respuesta.access_token);
          this.authService.guardarPerfilesInstitucionUsuario(respuesta.perfiles_instituciones);
          this.authService.guardarPerfilesInstitucionesPermisosUsuario(respuesta.perfiles_instituciones_permisos);
          this.bloqueoBtnIS = false;
          this.router.navigate([this.RUTA_REDIRECCION]);
          swal.fire('Bienvenido al sistema',
            '¡Hola ' + `${this.authService.usuario.nombre} ${this.authService.usuario.apellidos}!`,
            'success');
        },
        error => {
            this.bloqueoBtnIS = false;
            if (error.status === 400 ) {
              swal.fire('Datos de inicio de sesión incorrectos',
                'Usuario o contraseña incorrectos',
                'error');
            }
        }
      );
    }

}
