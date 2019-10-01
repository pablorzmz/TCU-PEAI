import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../../data/services/auth.service';
import {CONSTANTES} from '../../../../data/util/Constantes';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  private Constantes: CONSTANTES;

  constructor( private authService: AuthService, private router: Router) {
    this.Constantes = new CONSTANTES();
  }

  ngOnInit() {

  }

  cerrarSesion(): void {
    this.authService.cerrarSesion();
    this.router.navigate(['/auth/login']);

  }

  puedeVerInstituciones(): boolean {
    let resultado = false;
    const perfilesInstitciones = this.authService.obtenerPerfilesInstituciones();
    perfilesInstitciones.map(
      pi => {
        const idPerfil = pi.perfil as string;
        pi.instituciones.map(
          nombreInstitucion => {
              if (this.authService.tienePermisoEnPerfilInstitucion(this.Constantes.VISUALIZAR_INSTITUCIONES.ID,
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

  puedeVerAreasTematicas(): boolean {
    return true;
  }

  puedeVerCursos(): boolean {
    return true;
  }

}
