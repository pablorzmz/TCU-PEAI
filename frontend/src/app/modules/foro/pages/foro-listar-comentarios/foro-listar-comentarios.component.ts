import {Component, Input, OnInit} from '@angular/core';
import {ForoService} from '../../../../data/services/foro.service';
import {Usuario} from '../../../../data/schema/Usuario';
import {AuthService} from '../../../../data/services/auth.service';
import {MaterialPK} from '../../../../data/schema/MaterialPK';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-foro-listar-comentarios',
  templateUrl: './foro-listar-comentarios.component.html',
  styleUrls: ['./foro-listar-comentarios.component.css']
})
export class ForoListarComentariosComponent implements OnInit {

  constructor(private foroService: ForoService, private authService: AuthService) { }

  // Id del material del que se quiere recuperar los comentarios
  @Input() idMaterial: string;

  // Es la subseccion a la que pertenece
  @Input() idSubSeccion: number;


  // Aqui se guardan los comentarios
  comentarios: Array<any>;

  // EL usuario actual
  usuario: Usuario;

  // Indica si los comentarios se están cargando
  cargando: boolean;

  // Ordenado de mas reciente a mas antiguo
  ordenadoRecienteAntiguo = false;

  swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: 'btn btn-success m-1',
      cancelButton: 'btn btn-danger m-1'
    },
    buttonsStyling: false,
  });

  ngOnInit() {
    this.usuario = this.authService.usuario;
    this.obtenerComentarios();
  }

  /**
   * Pide los comentarios al servidor
   */
  obtenerComentarios(): any {
    this.cargando = true;
    this.foroService.getComentariosMaterial(this.idMaterial, this.idSubSeccion).subscribe(
      res => {
        this.comentarios = res;
        // Permite que el spiner se muestre al menos 1 sec
        setTimeout(() => this.cargando = false, 1000);
        if (this.ordenadoRecienteAntiguo === true) {
          this.comentarios = this.comentarios.reverse();
        }
      },
      err => {
        this.mensajeNoSePudieronObtenerComentarios();
        setTimeout(() => this.cargando = false, 1000);
      });
  }

  /**
   * Método que se activa cuando el componente AgregarComentario agrega un comentario
   * @param $event es el evento que ejecuta AgregarComentario
   */
  actualizarcomentarios($event): any {
    this.obtenerComentarios();
  }

  /**
   * Método que indica que los comentarios no se purieron obtener
   */
  mensajeNoSePudieronObtenerComentarios(): any {
    // Mensaje para indicar que no tiene los permisos
    this.swalWithBootstrapButtons.fire({
      position: 'center',
      type: 'error',
      title: 'No se pudo obtener los comentarios',
      text: 'Los comentarios de este material no están disponibles',
      confirmButtonText: 'Aceptar'
    });
  }

  /**
   * Método que reordena los comentarios por fecha
   */
  ordenarComentarios(): any {
    this.comentarios = this.comentarios.reverse();
    this.ordenadoRecienteAntiguo = !this.ordenadoRecienteAntiguo;
  }
}
