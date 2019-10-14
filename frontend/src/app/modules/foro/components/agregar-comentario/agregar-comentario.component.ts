import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ForoService} from '../../../../data/services/foro.service';
import {AuthService} from '../../../../data/services/auth.service';
import {Grupo} from '../../../../data/schema/Grupo';
import {UsuarioMaterialComenta} from '../../../../data/schema/UsuarioMaterialComenta';
import {MaterialPK} from '../../../../data/schema/MaterialPK';
import {UsuarioPK} from '../../../../data/schema/UsuarioPK';
import {UsuarioMaterialComentaPK} from '../../../../data/schema/UsuarioMaterialComentaPK';

@Component({
  selector: 'app-agregar-comentario',
  templateUrl: './agregar-comentario.component.html',
  styleUrls: ['./agregar-comentario.component.css']
})
export class AgregarComentarioComponent implements OnInit {

  constructor(private authService: AuthService, private foroService: ForoService) {
  }

  // Aqui se emite el evento de que se hizo el comentario
  @Output() valueChange = new EventEmitter<{comentario: any}>();

  // Form para controlar los campos
  comentarForm: FormGroup;


  ngOnInit() {
    // Inicializamos el form
    this.comentarForm = new FormBuilder().group({
      comentario: [null, [Validators.required, Validators.maxLength(100)]]
    });
  }

  agregarComentario(): any {

// ESTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    const usuarioMaterialComenta = new UsuarioMaterialComenta();

    const materialPK = new MaterialPK();
    materialPK.nombre = '1';
    materialPK.subSeccionMaterialId = 1;

    const usuarioPK = new UsuarioPK();
    usuarioPK.nombreUsuario = 'steveen';

    const usuarioMaterialComentaPK = new UsuarioMaterialComentaPK();
    usuarioMaterialComentaPK.usuario = usuarioPK;

    usuarioMaterialComentaPK.material = materialPK;

    usuarioMaterialComenta.id = usuarioMaterialComentaPK;

    usuarioMaterialComenta.textoComentario = this.comentarForm.get('comentario').value;

    usuarioMaterialComenta.visible = true;

    /*****************************/

    const request = this.foroService.setComentarioMaterial(usuarioMaterialComenta).subscribe(
      res => {
        const comentario = res;
        this.comentarForm.reset();
        this.valueChange.emit({comentario});
        request.unsubscribe();
      });

  }

}
