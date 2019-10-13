import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AreaTematicaRoutingModule } from './area-tematica-routing.module';
// tslint:disable-next-line:max-line-length
import { ListarAreasTematicasInstitucionComponent } from './pages/listar-areas-tematicas-institucion/listar-areas-tematicas-institucion.component';
import { AreaTematicaService } from '../../data/services/area-tematica.service';
import { ListarAreasTematicasComponent } from './pages/listar-areas-tematicas/listar-areas-tematicas.component';


@NgModule({
  declarations: [ListarAreasTematicasInstitucionComponent, ListarAreasTematicasComponent],
  exports: [
    ListarAreasTematicasInstitucionComponent
  ],
  imports: [
    CommonModule,
    AreaTematicaRoutingModule
  ],
  providers: [
    AreaTematicaService
  ]
})
export class AreaTematicaModule { }
