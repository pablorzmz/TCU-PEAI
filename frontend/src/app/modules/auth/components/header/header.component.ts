import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../../data/services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor( public authService: AuthService, private router: Router) {
  }

  ngOnInit() {

  }

  /**
   *  Método que utiliza el servicio de autorización para cerrar sesión y volver a login
   */
  cerrarSesion(): void {
    this.authService.cerrarSesion();
    this.router.navigate(['/auth/login']);
  }

}
