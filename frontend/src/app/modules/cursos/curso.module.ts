import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {ListarCursosComponent} from './pages/listar-cursos/listar-cursos.component';
import {CursoRoutingModule} from './curso-routing.module';
import {CursoService} from '../../data/services/curso.service';
import {CommonsModule} from '../commons/commons.module';
import { ListarCursosDeAreaComponent } from './pages/listar-cursos-de-area/listar-cursos-de-area.component';
import { CrearCursoComponent } from './components/crear-curso/crear-curso.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    ListarCursosComponent,
    ListarCursosDeAreaComponent,
    CrearCursoComponent
  ],
  exports: [
    ListarCursosComponent,
    ListarCursosDeAreaComponent
  ],
  imports: [
    CommonModule,
    CursoRoutingModule,
    HttpClientModule,
    CommonsModule,
    ReactiveFormsModule,
  ],
  providers: [
    CursoService
  ]
})
export class CursoModule { }
