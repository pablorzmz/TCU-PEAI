import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CONSTANTES} from '../../../../data/util/Constantes';
import {AuthService} from '../../../../data/services/auth.service';
import Swal from 'sweetalert2';
import {SubseccionMaterial} from '../../../../data/schema/SubseccionMaterial';

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

  // Contiene la configuracion del dialog
  dialogConfig: MatDialogConfig;

  // Mapeo como atributo para vista del modal.
  @ViewChild( ('modalCrearMaterial'), {static: false}) modalCrearMaterial: TemplateRef<any>;

  // Form para controlar los campos
  crearMaterialForm: FormGroup;

  // acceso a los valores constantes
  constantes: CONSTANTES;

  // para mostrar el progreso de carga del archivo
  progreso: number;

  constructor(private dialog: MatDialog, private authService: AuthService) {
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

}
