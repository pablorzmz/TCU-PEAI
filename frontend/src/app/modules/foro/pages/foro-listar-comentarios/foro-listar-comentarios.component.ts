import { Component, OnInit } from '@angular/core';
import {ForoService} from '../../../../data/services/foro.service';
import {Usuario} from '../../../../data/schema/Usuario';
import {AuthService} from '../../../../data/services/auth.service';

@Component({
  selector: 'app-foro-listar-comentarios',
  templateUrl: './foro-listar-comentarios.component.html',
  styleUrls: ['./foro-listar-comentarios.component.css']
})
export class ForoListarComentariosComponent implements OnInit {

  constructor(private foroService: ForoService, private authService: AuthService) { }

  // Aqui se guardan los comentarios
  comentarios: Array<any>;

  // EL usuario actual
  usuario: Usuario;

  ngOnInit() {
    this.usuario = this.authService.usuario;
    this.foroService.getComentariosMaterial('1', 1).subscribe(
      res => {
        this.comentarios = res;
      },
      err => {
        console.log(err);
      });
  }

  actualizarcomentarios($event): any {
    this.comentarios.push($event.comentario);
  }

}
