import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {Curso} from '../../../../data/schema/Curso';
import {Usuario} from '../../../../data/schema/Usuario';
import {AuthService} from '../../../../data/services/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Grupo} from '../../../../data/schema/Grupo';
import {GrupoService} from '../../../../data/services/grupo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-agregar-grupos-curso',
  templateUrl: './agregar-grupos-curso.component.html',
  styleUrls: ['./agregar-grupos-curso.component.css']
})
export class AgregarGruposCursoComponent implements OnInit {

  // Contiene la configuracion del dialog
  dialogConfig: MatDialogConfig;

  // Para saber el número de curso
  @Input() curso: Curso;

  // Aqui se emite el evento de que se recibió el grupo
  @Output() valueChange = new EventEmitter<{grupo: Grupo}>();

  // PopUp para mostrar la interfaz de carga de taxi
  @ViewChild('modalAgregarGrupo', {static: false}) modalCargarArchivo: TemplateRef<any>;

  // Form para controlar los campos
  agregarGrupoForm: FormGroup;

  // Usuario actual del sistema
  usuario: Usuario;

  swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: 'btn btn-success m-1',
      cancelButton: 'btn btn-danger m-1'
    },
    buttonsStyling: false,
  });

  constructor(private dialog: MatDialog, private authService: AuthService, private grupoService: GrupoService) {
    this.dialogConfig = new MatDialogConfig();
  }

  ngOnInit() {
    // Tomamos el nombre del usuario actual
    this.usuario = this.authService.usuario;
    // Iniciamos el form
    this.agregarGrupoForm = new FormBuilder().group({
      numeroGrupo: [null, [Validators.required, Validators.pattern('[0-9]{1,2}')]],
      periodoTiempo: [null, [Validators.required, Validators.maxLength(30)]],
      // En este caso solo se hace para cuando el usuario es profesor
      profesor: [this.usuario.usuarioPK.nombreUsuario, Validators.maxLength(50)],
    });
  }

  /**
   * Metodo para hacer los requiered de numero de grupo
   */
  get numeroGrupo() {
    return this.agregarGrupoForm.get('numeroGrupo');
  }

  /**
   * Metodo para hacer los requiered de periodo de tiempo
   */
  get periodoTiempo() {
    return this.agregarGrupoForm.get('periodoTiempo');
  }

  /**
   * Metodo para hacer los requiered de profesor
   */
  get profesor() {
    return this.agregarGrupoForm.get('profesor');
  }


  /**
   * Metodo que abre el pop up de agregar grupo.
   */
  abiriAgregarGrupo() {
    this.dialog.open(this.modalCargarArchivo, this.dialogConfig);
  }

  /**
   * Método que cierra el pop up
   */
  cerrarAgregarGrupo() {
    // Se resetean los valores
    this.agregarGrupoForm.get('numeroGrupo').reset();
    this.agregarGrupoForm.get('periodoTiempo').reset();
    this.dialog.closeAll();
  }

  /**
   * Metodo que hace el post
   */
   crearGrupo(): any {
     // Se verifica si tiene el permiso
    if (this.authService.validarTienePermisoEnAlgunPerfil(6)) {
      // Variable que almacena el grupo que será guardado
      let grupo: Grupo;
      // tslint:disable-next-line:max-line-length
      const request = this.grupoService.setGruposCurso(this.curso.id, this.numeroGrupo.value, this.periodoTiempo.value, this.profesor.value).subscribe(
        res => {
          // Si lo recibe se le asigna el grupo a la variable
          grupo = res;
          // Se cierra el dialog
          this.cerrarAgregarGrupo();
          // Se indica que el grupo ha sido creado
          this.mensajeGrupoCreado();
          // Se emite el cambio de que ya ha sido creado el grupo
          this.valueChange.emit({grupo});
          // Quitamos la subscripcion
          request.unsubscribe();
        },
        // En caso de que no se reciba correctamente se lanza una excepcion
        // Esto se implementa cuando esté el interceptor
        err => {
          this.manejarError(err);
        }
      );
    } else {
      this.mensajeNoTienePermisos();
    }
  }

  /**
   * Método que indica que el grupo ha sido creado
   */
  mensajeGrupoCreado(): any {
    // Mensaje para indicar que se creó
    this.swalWithBootstrapButtons.fire({
      position: 'center',
      type: 'success',
      title: 'Grupo creado',
      confirmButtonText: 'Aceptar'
    });
  }

  manejarError(err): any {
    // Si es un error de conflicto
    if (err.status === 409) {
      this.mensajeGrupoExiste(err.error.error);
    }
    // Aqui se pueden manejar otros posibles errores
  }

  /**
   * Método que indica que ha habido un error en la creación
   * @param err contiene el mensaje de error
   */
  mensajeGrupoExiste(err: string): any {
    // Mensaje para indicar que se creó
    this.swalWithBootstrapButtons.fire({
      position: 'center',
      type: 'error',
      title: 'El grupo ya existe',
      text: err,
      confirmButtonText: 'Aceptar'
    });
  }

  /**
   * Método que indica que el grupo ha sido creado
   */
  mensajeNoTienePermisos(): any {
    // Mensaje para indicar que no tiene los permisos
    this.swalWithBootstrapButtons.fire({
      position: 'center',
      type: 'error',
      title: 'No tiene permisos',
      text: 'No cuenta con los permisos para realizar esta acción',
      confirmButtonText: 'Aceptar'
    });
  }

}
