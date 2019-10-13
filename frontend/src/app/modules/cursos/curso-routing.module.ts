import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuardService} from '../../core/guard/auth.guard';
import {ListarCursosComponent} from './pages/listar-cursos/listar-cursos.component';
import {ListarCursosDeAreaComponent} from './pages/listar-cursos-de-area/listar-cursos-de-area.component';

const routes: Routes = [
  {
    path: 'cursos',
    component: ListarCursosComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'cursos/page/:page',
    component: ListarCursosComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'cursos/:area',
    component: ListarCursosDeAreaComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CursoRoutingModule { }
