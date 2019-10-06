import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {InstitucionesComponent} from './pages/listar-instituciones/instituciones.component';
import {AuthGuardService} from '../../core/guard/auth.guard';

const routes: Routes = [
  {
    path: 'instituciones',
    component: InstitucionesComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'instituciones/page/:page',
    component: InstitucionesComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstitucionRoutingModule { }
