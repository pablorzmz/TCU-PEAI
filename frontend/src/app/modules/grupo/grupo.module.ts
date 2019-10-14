import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GrupoRoutingModule } from './grupo-routing.module';
import { ListarGruposCursoComponent } from './pages/listar-grupos-curso/listar-grupos-curso.component';
import { GrupoService } from '../../data/services/grupo.service';
import { AgregarGruposCursoComponent } from './components/agregar-grupos-curso/agregar-grupos-curso.component';
import { MatDialogModule } from '@angular/material';
import {MatIconModule} from '@angular/material';
import {ReactiveFormsModule} from '@angular/forms';
// tslint:disable-next-line:max-line-length
import { AgregarSubseccionMaterialGrupoComponent } from './components/agregar-subseccion-material-grupo/agregar-subseccion-material-grupo.component';
import {ForoModule} from '../foro/foro.module';


@NgModule({
  declarations: [ListarGruposCursoComponent, AgregarGruposCursoComponent, AgregarSubseccionMaterialGrupoComponent],
  exports: [
    ListarGruposCursoComponent,
    AgregarSubseccionMaterialGrupoComponent
  ],
  imports: [
    CommonModule,
    GrupoRoutingModule,
    MatDialogModule,
    MatIconModule,
    ReactiveFormsModule,
    ForoModule
  ],
  providers: [
    GrupoService
  ]
})
export class GrupoModule { }
