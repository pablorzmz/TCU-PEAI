import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuardService} from '../../core/guard/auth.guard';
import {VistaPrincipalCursoComponent} from './pages/principal/vista-principal-curso.component';
import {VerMaterialComponent} from '../grupo/components/ver-material/ver-material.component';


const routes: Routes = [
  {
    path: 'vista_principal_curso/:id',
    component: VistaPrincipalCursoComponent,
    canActivate: [AuthGuardService],
    children: [
      {
        path: 'material/:idMaterial/:idSubSeccion',
        component: VerMaterialComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class VistaPrincipalCursoRoutingModule {}
