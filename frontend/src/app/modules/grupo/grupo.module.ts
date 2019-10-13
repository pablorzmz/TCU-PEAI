import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GrupoRoutingModule } from './grupo-routing.module';
import { ListarGruposCursoComponent } from './pages/listar-grupos-curso/listar-grupos-curso.component';
import { GrupoService } from '../../data/services/grupo.service';
import { AgregarGruposCursoComponent } from './components/agregar-grupos-curso/agregar-grupos-curso.component';
import { MatDialogModule } from '@angular/material';
import {MatIconModule} from '@angular/material';
import {ReactiveFormsModule} from '@angular/forms';
import { AgregarSubseccionMaterialGrupoComponent } from
    './components/agregar-subseccion-material-grupo/agregar-subseccion-material-grupo.component';
import { EliminarSubseccionMaterialGrupoComponent } from
    './components/eliminar-subseccion-material-grupo/eliminar-subseccion-material-grupo.component';


@NgModule({
  declarations: [ListarGruposCursoComponent, AgregarGruposCursoComponent,
    AgregarSubseccionMaterialGrupoComponent, EliminarSubseccionMaterialGrupoComponent],
  exports: [
    ListarGruposCursoComponent,
    AgregarSubseccionMaterialGrupoComponent,
    EliminarSubseccionMaterialGrupoComponent
  ],
  imports: [
    CommonModule,
    GrupoRoutingModule,
    MatDialogModule,
    MatIconModule,
    ReactiveFormsModule
  ],
  providers: [
    GrupoService
  ]
})
export class GrupoModule { }
