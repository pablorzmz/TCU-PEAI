import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {Curso} from '../../../../data/schema/Curso';
import {AuthService} from '../../../../data/services/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import Swal from 'sweetalert2';
import {CONSTANTES} from '../../../../data/util/Constantes';
import {CursoService} from '../../../../data/services/curso.service';
import {Institucion} from '../../../../data/schema/Institucion';
import {AreaTematicaService} from '../../../../data/services/area-tematica.service';
import {InstitucionService} from '../../../../data/services/institucion.service';
import {AreaTematica} from '../../../../data/schema/AreaTematica';

@Component({
  selector: 'app-crear-curso',
  templateUrl: './crear-curso.component.html',
  styleUrls: ['./crear-curso.component.css']
})
export class CrearCursoComponent implements OnInit {

  // Contiene la configuracion del dialog
  dialogConfig: MatDialogConfig;

  // Para saber el número de curso
  @Input() idArea: number;

  // Aqui se emite el evento de que se recibió el grupo
  @Output() valueChange = new EventEmitter<{curso: Curso}>();

  // PopUp para mostrar la interfaz de carga de taxi
  @ViewChild('modalCrearCurso', {static: false}) modalCargarArchivo: TemplateRef<any>;

  // Usuario actual del sistema
  usuario: string;

  // Form para controlar los campos
  crearCursoForm: FormGroup;

  // Lista de Instituciones
  instituciones: Institucion[];

  // Lista de areas Tematicas
  areasTematicas: AreaTematica[];

  // Se obtienen las contantes de permisos
  constantes = new CONSTANTES();

  swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: 'btn btn-success m-1',
      cancelButton: 'btn btn-danger m-1'
    },
    buttonsStyling: false,
  });

  constructor(private dialog: MatDialog,
              private authService: AuthService,
              private cursoService: CursoService,
              private areaTematicaService: AreaTematicaService,
              private institucionService: InstitucionService) {
    this.dialogConfig = new MatDialogConfig();
  }

  ngOnInit() {
    // Tomamos el nombre del usuario actual
    this.usuario = this.authService.usuario.usuarioPK.nombreUsuario;
    // Si no hay area tematica
    if (this.idArea == null) {
      // hay que obtener toda la info
      const request = this.institucionService.getInstituciones().subscribe(
        (response) => {
          // Si se reciben las instituciones se le asigna al atributo intituciones la lista de ellas
          this.instituciones = response as Institucion[];
          // Se elimina la subscripción
          request.unsubscribe();
        });
    } else { // Si si hay area tematica por parametro
      this.instituciones = new Array();
      this.areasTematicas = new Array();
      const request = this.areaTematicaService.getAreaTematicaById(this.idArea).subscribe(
      (response) => {
        // Si se reciben la info
        const aT: AreaTematica = response as AreaTematica;
        // Metemos el area tematica a la lista
        this.areasTematicas.push(aT);
        // Metemos la institucion a la lista
        this.instituciones.push(aT.institucion);
        // Se elimina la subscripción
        request.unsubscribe();
      });
    }

    // Iniciamos el form
    this.crearCursoForm = new FormBuilder().group({
      nombreCurso: [null, [Validators.required, Validators.maxLength(250)]],
      descripcionCurso: [null, [Validators.required, Validators.maxLength(250)]],
      institucionCurso: ['-', [Validators.required, Validators.minLength(2)]],
      areaCurso: ['-', [Validators.required, Validators.minLength(2)]]
    });
  }

  /**
   * Metodo para hacer los requiered de nombre del Curso
   */
  get nombreCurso() {
    return this.crearCursoForm.get('nombreCurso');
  }

  /**
   * Metodo para hacer los requiered de descripcion del Curso
   */
  get descripcionCurso() {
    return this.crearCursoForm.get('descripcionCurso');
  }

  /**
   * Metodo para hacer los requiered de area tematica del Curso
   */
  get areaCurso() {
    return this.crearCursoForm.get('areaCurso');
  }

  /**
   * Metodo para hacer los requiered de institucion del Curso
   */
  get institucionCurso() {
    return this.crearCursoForm.get('institucionCurso');
  }


  /**
   * Metodo que abre el pop up de agregar curso.
   */
  abrirCrearCurso() {
    this.dialog.open(this.modalCargarArchivo, this.dialogConfig);
  }

  /**
   * Método que cierra el pop up
   */
  cerrarCrearCurso() {
    // Se resetean los valores
    this.crearCursoForm.get('nombreCurso').reset();
    this.crearCursoForm.get('descripcionCurso').reset();
  }

  /**
   * Metodo que hace el post
   */
  crearCurso(): any {
    // Se verifica si tiene el permiso
    if (this.authService.validarTienePermisoEnAlgunPerfilDeInstitucion(this.constantes.CREAR_CURSO.ID, this.institucionCurso.value)) {
      // Variable que almacena el grupo que será guardado
      const cursoN: Curso = new Curso();
      cursoN.nombre = this.nombreCurso.value;
      cursoN.descripcion = this.descripcionCurso.value;
      // tslint:disable-next-line:max-line-length
      const request = this.cursoService.createCurso(cursoN, this.areaCurso.value, this.usuario).subscribe(
        res => {
          // Si lo recibe se le asigna el grupo a la variable
          const curso = res as Curso;
          // Se cierra el dialog
          this.cerrarCrearCurso();
          // Se indica que el grupo ha sido creado
          this.mensajeCursoCreado();
          // Se emite el cambio de que ya ha sido creado el grupo
          this.valueChange.emit({curso});
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
  mensajeCursoCreado(): any {
    // Mensaje para indicar que se creó
    this.swalWithBootstrapButtons.fire({
      position: 'center',
      type: 'success',
      title: 'Curso creado',
      confirmButtonText: 'Aceptar'
    });
  }

  manejarError(err): any {
    // Si es un error de conflicto
    if (err.status === 409) {
      this.mensajeCursoExiste(err.error.error);
    }
    // Aqui se pueden manejar otros posibles errores
  }

  /**
   * Método que indica que ha habido un error en la creación
   * @param err contiene el mensaje de error
   */
  mensajeCursoExiste(err: string): any {
    // Mensaje para indicar que se creó
    this.swalWithBootstrapButtons.fire({
      position: 'center',
      type: 'error',
      title: 'El curso ya existe',
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

  cargarAreasTematicas(): void {
    // Si no vengo de un area tematica especifica
    if (this.idArea == null) {
      // hay que obtener las areas tematicas
      const nombre: string = this.institucionCurso.value;
      const request = this.areaTematicaService.getAreasTematicasInstitucion(nombre).subscribe(
        (response) => {
          // Si se reciben las instituciones se le asigna al atributo intituciones la lista de ellas
          this.areasTematicas = response as AreaTematica[];
          // Se elimina la subscripción
          request.unsubscribe();
        });
    }
  }

}
