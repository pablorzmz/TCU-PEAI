import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ForoRoutingModule } from './foro-routing.module';
import { MensajeComponent } from './components/mensaje/mensaje.component';
import {ForoService} from '../../data/services/foro.service';
import { ForoListarComentariosComponent } from './pages/foro-listar-comentarios/foro-listar-comentarios.component';


@NgModule({
  declarations: [MensajeComponent, ForoListarComentariosComponent],
  imports: [
    CommonModule,
    ForoRoutingModule
  ],
  exports: [
    MensajeComponent,
    ForoListarComentariosComponent
  ],
  providers: [ForoService]
})
export class ForoModule { }
