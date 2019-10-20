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
import { CrearMaterialSubseccionComponent } from './components/crear-material-subseccion/crear-material-subseccion.component';
import { ListaEstudiantesGrupoComponent } from './pages/lista-estudiantes-grupo/lista-estudiantes-grupo.component';
import { EliminarMaterialGrupoComponent } from './components/eliminar-material-grupo/eliminar-material-grupo.component';
import {ForoModule} from '../foro/foro.module';
import { VerMaterialComponent } from './components/ver-material/ver-material.component';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';


@NgModule({
  declarations: [ListarGruposCursoComponent, AgregarGruposCursoComponent,
    AgregarSubseccionMaterialGrupoComponent, EliminarSubseccionMaterialGrupoComponent, EditarSubseccionMaterialGrupoComponent,
    EditarSubseccionMaterialGrupoComponent, ListaEstudiantesGrupoComponent, CrearMaterialSubseccionComponent,
    EliminarMaterialGrupoComponent, VerMaterialComponent],
  exports: [
    ListarGruposCursoComponent,
    AgregarSubseccionMaterialGrupoComponent,
    EliminarSubseccionMaterialGrupoComponent,
    EditarSubseccionMaterialGrupoComponent,
    CrearMaterialSubseccionComponent,
    EliminarMaterialGrupoComponent
  ],
  imports: [
    CommonModule,
    GrupoRoutingModule,
    MatDialogModule,
    MatIconModule,
    ReactiveFormsModule,
    FormsModule,
    ForoModule,
    NgxExtendedPdfViewerModule
  ],
  providers: [
    GrupoService
  ]
})
export class GrupoModule { }
