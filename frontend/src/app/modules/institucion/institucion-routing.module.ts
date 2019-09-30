import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarAreasTematicasComponent } from './pages/listar-areas-tematicas/listar-areas-tematicas.component';


const routes: Routes = [
   {
    path: 'areas_tematicas/:nombreInstitucion',
    pathMatch: 'full',
    component: ListarAreasTematicasComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstitucionRoutingModule { }
