import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AreaTematicaRoutingModule } from './area-tematica-routing.module';
import { AreaTematicaListarComponent } from './pages/area-tematica-listar/area-tematica-listar.component';


@NgModule({
  declarations: [AreaTematicaListarComponent],
  imports: [
    CommonModule,
    AreaTematicaRoutingModule
  ]
})
export class AreaTematicaModule { }
