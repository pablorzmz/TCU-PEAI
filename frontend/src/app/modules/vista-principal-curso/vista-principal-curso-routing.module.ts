import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuardService} from '../../core/guard/auth.guard';
import {VistaPrincipalCursoComponent} from './pages/principal/vista-principal-curso.component';


const routes: Routes = [
  {
    path: 'vista_principal_curso/:id',
    pathMatch: 'full',
    component: VistaPrincipalCursoComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class VistaPrincipalCursoRoutingModule {}
