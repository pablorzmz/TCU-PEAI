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
  // Metodo para hacer los requiered de numero de grupo
  get numeroGrupo() {
    return this.agregarGrupoForm.get('numeroGrupo');
  }

  // Metodo para hacer los requiered de periodo de tiempo
  get periodoTiempo() {
    return this.agregarGrupoForm.get('periodoTiempo');
  }

  // Metodo para hacer los requiered de profesor
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
    // Variable que almacena el grupo que será guardado
     let grupo: Grupo;
    // tslint:disable-next-line:max-line-length
     const request = this.grupoService.setGruposCurso(this.curso.id.id, this.numeroGrupo.value, this.periodoTiempo.value, this.profesor.value).subscribe(
      res => {
        // Si lo recibe se le asigna el grupo a la variable
        grupo = res;
        // Se cierra el dialog
        this.cerrarAgregarGrupo();
        // Se indica que el grupo ha sido creado
        this.grupoCreado();
        // Se emite el cambio de que ya ha sido creado el grupo
        this.valueChange.emit({grupo});
        // Quitamos la subscripcion
        request.unsubscribe();
      },
      // En caso de que no se reciba correctamente se lanza una excepcion
      // Esto se implementa cuando esté el interceptor
      err => {
        console.error(err);
        return null;
      }
    );
  }

  /**
   * Método que indica que el grupo ha sido creado
   */
  grupoCreado(): any {
    // Mensaje para indicar que se creó
    this.swalWithBootstrapButtons.fire({
      position: 'center',
      type: 'success',
      title: 'Grupo creado',
      confirmButtonText: 'Aceptar'
    });
  }

}
