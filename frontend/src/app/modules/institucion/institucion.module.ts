import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstitucionRoutingModule } from './institucion-routing.module';
import { ListarAreasTematicasComponent } from './pages/listar-areas-tematicas/listar-areas-tematicas.component';
import {InstitucionService} from '../../data/services/institucion.service';


@NgModule({
  declarations: [ListarAreasTematicasComponent],
  exports: [
    ListarAreasTematicasComponent
  ],
  imports: [
    CommonModule,
    InstitucionRoutingModule
  ],
  providers: [
    InstitucionService
  ]
})
export class InstitucionModule { }
