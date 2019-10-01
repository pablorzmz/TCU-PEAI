import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InstitucionesComponent} from './pages/listar-instituciones/instituciones.component';
import {InstitucionRoutingModule} from './institucion-routing.module';
import {InstitucionService} from '../../data/services/institucion.service';
import {PaginatorComponent} from './components/paginator/paginator.component';
import {HttpClientModule} from '@angular/common/http';


@NgModule({
  declarations: [
    InstitucionesComponent,
    PaginatorComponent
  ],
  exports: [
    InstitucionesComponent
  ],
  imports: [
    CommonModule,
    InstitucionRoutingModule,
    HttpClientModule
  ],
  providers: [
    InstitucionService
  ]
})
export class InstitucionModule { }
