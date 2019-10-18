import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CONSTANTES} from '../../../../data/util/Constantes';
import {AuthService} from '../../../../data/services/auth.service';
import Swal from 'sweetalert2';
import {SubseccionMaterial} from '../../../../data/schema/SubseccionMaterial';
import {MaterialService} from '../../../../data/services/material.service';
import {HttpEventType} from '@angular/common/http';
import {Material} from '../../../../data/schema/Material';
import {Grupo} from '../../../../data/schema/Grupo';

@Component({
  selector: 'app-crear-material-subseccion',
  templateUrl: './crear-material-subseccion.component.html',
  styleUrls: ['./crear-material-subseccion.component.css']
})
export class CrearMaterialSubseccionComponent implements OnInit {

  // Para controlar el archivo actualmente seleccionado
  private archivoSeleccionado: File;

  // La subsección de material actual para agregar el material
  @Input() sbm: SubseccionMaterial;

  // Grupo al que pertenece el material
  @Input() grupo: Grupo;

  // Contiene la configuracion del dialog
  dialogConfig: MatDialogConfig;

  // Mapeo como atributo para vista del modal.
  @ViewChild( ('modalCrearMaterial'), {static: false}) modalCrearMaterial: TemplateRef<any>;

  // Aqui se emite el evento para eliminar la subsección de la vista
  @Output() nuevoMaterialValueChange = new EventEmitter<{material: Material}>();

  // Form para controlar los campos
  crearMaterialForm: FormGroup;

  // acceso a los valores constantes
  constantes: CONSTANTES;

  // para mostrar el progreso de carga del archivo
  progreso: number;

  constructor(private dialog: MatDialog, private authService: AuthService, private materialService: MaterialService) {
    this.dialogConfig = new MatDialogConfig();
    this.constantes = new CONSTANTES();
  }

  ngOnInit() {
    // Inicialización del formulario
    this.crearMaterialForm = new FormBuilder().group({
      nombreMaterial: [ null, [Validators.required, Validators.maxLength(30)]],
      descripcionMaterial: [ null, [Validators.maxLength(100)]],
    });
  }


  /**
   * Obtener el nombre del material para el form
   */
  get nombreMaterial(): any {
   return this.crearMaterialForm.get('nombreMaterial');
  }

  /**
   * Retornal la descripción para el formulario
   */
  get descripcionMaterial(): any {
    return this.crearMaterialForm.get('descripcionMaterial');
  }

  /**
   * Metodo para cerrar el modal
   */
  cerrarCrearModal(): void {
    this.crearMaterialForm.get('nombreMaterial').reset();
    this.crearMaterialForm.get('descripcionMaterial').reset();
    this.archivoSeleccionado = null;
    this.dialog.closeAll();
  }

  /**
   * Metodo para mostrar el modal
   */
  abrirCrearModal(): void {
    this.dialog.open(this.modalCrearMaterial, this.dialogConfig);
  }

  /**
   * Metodo para subir el archivo
   */
  seleccionarArchivo(event): void {
    this.archivoSeleccionado = event.target.files[0];
    this.progreso = 0;
    if (this.archivoSeleccionado.type.indexOf('application/pdf') < 0) {
      Swal.fire ({
          title: 'Formato de archivo incorrecto',
          text: 'Por favor, seleccione un archivo PDF',
          type: 'error',
          confirmButtonText: 'Aceptar'
      });
      this.archivoSeleccionado = null;
    }
  }

  /**
   * Metodo que permite validar si tiene el permiso para crear un nuevo material.
   */
  puedeCrearMaterial(): boolean {
    return this.authService.validarTienePermisoEnAlgunPerfilDeInstitucion(this.constantes.AGREGAR_SUBSECCION_MATERIAL_GRUPO.ID,
      this.grupo.curso.areaTematica.institucion.institucionPK.nombre);
  }

  /**
   * Método para crear el material cuando todo sea válido
   */
  crearMaterial() {
    // se hacer la peticion con el servicio
    const request = this.materialService.crearMaterial(
      this.nombreMaterial.value,
      this.descripcionMaterial.value,
      this.sbm.id,
      this.archivoSeleccionado ).subscribe(
      event => {
        // Se revisa si es notificación del progreso
        if ( event.type === HttpEventType.UploadProgress ) {
          this.progreso = Math.round(100 * event.loaded / event.total);
          // se verifica si es que ya terminó el response
        } else if ( event.type === HttpEventType.Response ) {
          // se obtiene el response mejor por aparte
          const response: any = event.body;
          // Se emite el cambio
          this.nuevoMaterialValueChange.emit( { material: response.material as Material });
          // se notifica de éxito
          Swal.fire({
            title: '¡Éxito al crear el material!',
            text: response.mensaje,
            type: 'success',
            confirmButtonText: 'Aceptar'
          });
          // se desubscribe
          request.unsubscribe();
          // se cierra el modal
          this.cerrarCrearModal();
        }
      },
      // Se maneja el error
        error => {
        Swal.fire({
          title: 'Ocurrió un error al crear el material',
          text: error.error.error,
          type: 'error',
          confirmButtonText: 'Aceptar'
        });
        // se reinicia el progreso
        this.progreso = 0;
      }
    );
  }
}
