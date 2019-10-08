import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuardService} from '../../core/guard/auth.guard';
import {ListarGruposCursoComponent} from './pages/listar-grupos-curso/listar-grupos-curso.component';


const routes: Routes = [
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GrupoRoutingModule {

}
