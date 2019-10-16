import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ForoRoutingModule } from './foro-routing.module';
import {ComentarioComponent} from './components/comentario/comentario.component';
import {ForoService} from '../../data/services/foro.service';
import { ForoListarComentariosComponent } from './pages/foro-listar-comentarios/foro-listar-comentarios.component';
import { AgregarComentarioComponent } from './components/agregar-comentario/agregar-comentario.component';
import {ReactiveFormsModule} from '@angular/forms';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';


@NgModule({
  declarations: [ComentarioComponent, ForoListarComentariosComponent, AgregarComentarioComponent],
  imports: [
    CommonModule,
    ForoRoutingModule,
    ReactiveFormsModule,
    NgxExtendedPdfViewerModule
  ],
  exports: [
    ComentarioComponent,
    ForoListarComentariosComponent
  ],
  providers: [ForoService]
})
export class ForoModule { }
