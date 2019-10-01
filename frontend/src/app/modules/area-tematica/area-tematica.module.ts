import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AreaTematicaRoutingModule } from './area-tematica-routing.module';
import { ListarAreasTematicasComponent } from './pages/listar-areas-tematicas/listar-areas-tematicas.component';
import { AreaTematicaService } from '../../data/services/area-tematica.service';


@NgModule({
  declarations: [ListarAreasTematicasComponent],
  exports: [
    ListarAreasTematicasComponent
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
