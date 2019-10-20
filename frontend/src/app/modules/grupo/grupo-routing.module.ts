import { NgModule } from '@angular/core';
import {Routes, RouterModule, ExtraOptions} from '@angular/router';
import {AuthGuardService} from '../../core/guard/auth.guard';
import {ListaEstudiantesGrupoComponent} from './pages/lista-estudiantes-grupo/lista-estudiantes-grupo.component';

const routerOptions: ExtraOptions = {
  useHash: false,
  anchorScrolling: 'enabled'
};

const routes: Routes = [
  {
    path: 'agregar_estudiantes_grupo/:institucion/:curso/:numGrupo/:periodoT',
    component: ListaEstudiantesGrupoComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    RouterModule.forRoot(routes, routerOptions)
  ],
  exports: [RouterModule]
})
export class GrupoRoutingModule {

}
