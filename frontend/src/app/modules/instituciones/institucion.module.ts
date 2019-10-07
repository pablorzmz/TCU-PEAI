import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InstitucionesComponent} from './pages/listar-instituciones/instituciones.component';
import {InstitucionRoutingModule} from './institucion-routing.module';
import {InstitucionService} from '../../data/services/institucion.service';
import {HttpClientModule} from '@angular/common/http';
import {CommonsModule} from '../commons/commons.module';


@NgModule({
  declarations: [
    InstitucionesComponent
  ],
  exports: [
    InstitucionesComponent
  ],
  imports: [
    CommonModule,
    InstitucionRoutingModule,
    HttpClientModule,
    CommonsModule
  ],
  providers: [
    InstitucionService
  ]
})
export class InstitucionModule { }
