import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GrupoRoutingModule } from './grupo-routing.module';
import { ListarGruposCursoComponent } from './pages/listar-grupos-curso/listar-grupos-curso.component';
import { GrupoService } from '../../data/services/grupo.service';
import { AgregarGruposCursoComponent } from './components/agregar-grupos-curso/agregar-grupos-curso.component';
import { MatDialogModule } from '@angular/material';
import {MatIconModule} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AgregarSubseccionMaterialGrupoComponent } from
    './components/agregar-subseccion-material-grupo/agregar-subseccion-material-grupo.component';
import { EliminarSubseccionMaterialGrupoComponent } from
    './components/eliminar-subseccion-material-grupo/eliminar-subseccion-material-grupo.component';
import { EditarSubseccionMaterialGrupoComponent } from
    './components/editar-subseccion-material-grupo/editar-subseccion-material-grupo.component';
import { ListaEstudiantesGrupoComponent } from './pages/lista-estudiantes-grupo/lista-estudiantes-grupo.component';


@NgModule({
  declarations: [ListarGruposCursoComponent, AgregarGruposCursoComponent,
    AgregarSubseccionMaterialGrupoComponent, EliminarSubseccionMaterialGrupoComponent,
    EditarSubseccionMaterialGrupoComponent, ListaEstudiantesGrupoComponent],
  exports: [
    ListarGruposCursoComponent,
    AgregarSubseccionMaterialGrupoComponent,
    EliminarSubseccionMaterialGrupoComponent,
    EditarSubseccionMaterialGrupoComponent
  ],
  imports: [
    CommonModule,
    GrupoRoutingModule,
    MatDialogModule,
    MatIconModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    GrupoService
  ]
})
export class GrupoModule { }
