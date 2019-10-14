import { Component, OnInit } from '@angular/core';
import {ForoService} from '../../../../data/services/foro.service';

@Component({
  selector: 'app-foro-listar-comentarios',
  templateUrl: './foro-listar-comentarios.component.html',
  styleUrls: ['./foro-listar-comentarios.component.css']
})
export class ForoListarComentariosComponent implements OnInit {

  constructor(private foroService: ForoService) { }

  // Aqui se guardan los comentarios
  comentarios: Array<any>;

  ngOnInit() {
    this.foroService.getComentariosMaterial('1', 1).subscribe(
      res => {
        this.comentarios = res;
      },
      err => {
        console.log(err);
      });
  }

  /*
      // ESTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    const materialPK = new MaterialPK();
    materialPK.nombre = '1';
    materialPK.subSeccionMaterialId = 1;

    const usuarioPK = new UsuarioPK();
    usuarioPK.nombreUsuario = 'steveen';

    const usuarioMaterialComentaPK = new UsuarioMaterialComentaPK();
    usuarioMaterialComentaPK.usuario = usuarioPK;

    usuarioMaterialComentaPK.material = materialPK;

    this.usuarioMaterialComenta.id = usuarioMaterialComentaPK;

    this.usuarioMaterialComenta.textoComentario = 'Madre miaaaa';

    this.usuarioMaterialComenta.visible = true;

    // ESTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
   */
}
