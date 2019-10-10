import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GrupoRoutingModule } from './grupo-routing.module';
import { ListarGruposCursoComponent } from './pages/listar-grupos-curso/listar-grupos-curso.component';
import { GrupoService } from '../../data/services/grupo.service';


@NgModule({
  declarations: [ListarGruposCursoComponent],
  exports: [
    ListarGruposCursoComponent
  ],
  imports: [
    CommonModule,
    GrupoRoutingModule
  ],
  providers: [
    GrupoService
  ]
})
export class GrupoModule { }
