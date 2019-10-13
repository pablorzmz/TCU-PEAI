import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
// tslint:disable-next-line:max-line-length
import { ListarAreasTematicasInstitucionComponent } from './pages/listar-areas-tematicas-institucion/listar-areas-tematicas-institucion.component';
import {AuthGuardService} from '../../core/guard/auth.guard';
import {ListarAreasTematicasComponent} from './pages/listar-areas-tematicas/listar-areas-tematicas.component';


const routes: Routes = [
  {
    path: 'areas_tematicas',
    pathMatch: 'full',
    component: ListarAreasTematicasComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'areas_tematicas_institucion/:nombreInstitucion',
    pathMatch: 'full',
    component: ListarAreasTematicasInstitucionComponent,
     canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AreaTematicaRoutingModule { }
