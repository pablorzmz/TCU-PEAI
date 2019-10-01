import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../../data/services/auth.service';

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(public auth: AuthService, public router: Router) {}

  canActivate(): boolean {
  if (!this.auth.usuarioEstaLogeado()) {
    this.router.navigate(['/auth/login']);
    return false;
  }
  return true;
}}
