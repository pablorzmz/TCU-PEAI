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
  constructor(private  authService: AuthService,
              private router: Router) {
    this.usuario = new Usuario();
  }

  ngOnInit() {
    swal.fire('SweetAlert2 funcionando!',
      'Instalado correctamente',
      'success');
  }

  mensajeRegistro(): void {
    swal.fire('Estamos trabajando en esta funcionalidad',
      'El formulario de registro aún no se encuentra disponible',
      'info');
    }

    login(): void {
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
          console.log(respuesta);
          // Se guardan el usuario y el token, perfiles, permisos e instituciones
          this.authService.guardarUsuario(respuesta.access_token);
          this.authService.guardarToken(respuesta.access_token);
          this.authService.guardarPerfilesInstitucionUsuario(respuesta.perfiles_instituciones);
          // TODO redirigir a la vista principal de instituciones
          this.router.navigate(['/register']);
          swal.fire('Bienvenido al sistema',
            '¡Hola ' + `${this.authService.usuario.nombre} ${this.authService.usuario.apellidos}!`,
            'success');
        }
      );
    }

}
