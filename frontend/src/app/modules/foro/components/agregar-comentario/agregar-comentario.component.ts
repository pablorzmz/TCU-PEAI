import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ForoService} from '../../../../data/services/foro.service';
import {AuthService} from '../../../../data/services/auth.service';
import {Grupo} from '../../../../data/schema/Grupo';
import {UsuarioMaterialComenta} from '../../../../data/schema/UsuarioMaterialComenta';
import {MaterialPK} from '../../../../data/schema/MaterialPK';
import {UsuarioPK} from '../../../../data/schema/UsuarioPK';
import {UsuarioMaterialComentaPK} from '../../../../data/schema/UsuarioMaterialComentaPK';
import {Usuario} from '../../../../data/schema/Usuario';

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

  // Es el id del material el cual se quiere comentar
  @Input() materialPK: MaterialPK;

  // Form para controlar los campos
  comentarForm: FormGroup;

  // Se usa para saber si se está enviando el comentario
  enviando = false;

  // Usuario actual
  usuario: Usuario;

  ngOnInit() {
    // Tomamos el nombre del usuario actual
    this.usuario = this.authService.usuario;
    // Inicializamos el form
    this.comentarForm = new FormBuilder().group({
      comentario: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]]
    });
  }

  /**
   * Método que envia el comentario al servidor
   */
  agregarComentario(): any {

    // Se indica que se está enviando el comentario
    this.enviando = true;

    // Se crea la instancia
    const usuarioMaterialComenta = this.crearInstancia();

    // Se envia al servidor
    const request = this.foroService.setComentarioMaterial(usuarioMaterialComenta).subscribe(
      res => {
        const comentario = res;
        this.comentarForm.reset();
        this.valueChange.emit({comentario});
        request.unsubscribe();
        this.enviando = false;
      }, error => {
        console.log(error);
        this.enviando = false;
      });

  }

  /**
   * Método que crea la instancia de UsuarioMaterialComenta
   */
  crearInstancia(): UsuarioMaterialComenta {
    // Se crea la instancia a enviar
    const usuarioMaterialComenta = new UsuarioMaterialComenta();

    // Se necesita usuarioPK
    const usuarioPK = new UsuarioPK();
    usuarioPK.nombreUsuario = this.usuario.usuarioPK.nombreUsuario;

    // Se necesita crea y setea el PK de la instancia a enviar
    const usuarioMaterialComentaPK = new UsuarioMaterialComentaPK();
    usuarioMaterialComentaPK.usuario = usuarioPK;
    usuarioMaterialComentaPK.material = this.materialPK;

    // Se agrega el PK de la instancia
    usuarioMaterialComenta.id = usuarioMaterialComentaPK;

    // Finalmente se agrega el texto y se pone como visible
    usuarioMaterialComenta.textoComentario = this.comentarForm.get('comentario').value;
    usuarioMaterialComenta.visible = true;

    return usuarioMaterialComenta;
  }

}
