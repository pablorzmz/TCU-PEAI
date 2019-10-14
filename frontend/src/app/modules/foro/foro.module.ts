import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ForoRoutingModule } from './foro-routing.module';
import { MensajeComponent } from './components/mensaje/mensaje.component';
import {ForoService} from '../../data/services/foro.service';


@NgModule({
  declarations: [MensajeComponent],
  imports: [
    CommonModule,
    ForoRoutingModule
  ],
  exports: [
    MensajeComponent
  ],
  providers: [ForoService]
})
export class ForoModule { }
