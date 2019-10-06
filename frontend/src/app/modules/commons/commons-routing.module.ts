import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuardService} from '../../core/guard/auth.guard';
import {ListarCursosComponent} from '../cursos/pages/listar-cursos/listar-cursos.component';
import {InstitucionesComponent} from '../instituciones/pages/listar-instituciones/instituciones.component';

const routes: Routes = [
  {
    path: 'instituciones/page/:page',
    component: InstitucionesComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'cursos/page/:page',
    component: ListarCursosComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CommonsRoutingModule { }
