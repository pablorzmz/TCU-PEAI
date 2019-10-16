import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../../../data/services/auth.service';
import {SubseccionMaterial} from '../../../../data/schema/SubseccionMaterial';
import {CONSTANTES} from '../../../../data/util/Constantes';
import {Grupo} from '../../../../data/schema/Grupo';
import {SubseccionMaterialService} from '../../../../data/services/subseccion-material.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar-subseccion-material-grupo',
  templateUrl: './editar-subseccion-material-grupo.component.html',
  styleUrls: ['./editar-subseccion-material-grupo.component.css']
})
export class EditarSubseccionMaterialGrupoComponent implements OnInit {

  // Nombre de institucion para la validación del permiso
  @Input() grupo: Grupo;

  // La subsección a editar
  @Input() sbmAEditar: SubseccionMaterial;

  // Aqui se emite el evento para actualizar la subsección de la vista
  @Output() sbmActualizarValueChange = new EventEmitter<{sbm: SubseccionMaterial}>();

  // Contiene la configuracion del dialog
  dialogConfig: MatDialogConfig;

  // Mapeo como atributo para vista del modal.
  @ViewChild( ('modalEditarSBM'), {static: false}) modalEditarSBM: TemplateRef<any>;

  // Form para controlar los campos
  editarSBMForm: FormGroup;

  // acceso a los valores constantes
  constantes: CONSTANTES;

  constructor(private dialog: MatDialog, private authService: AuthService, private sbmService: SubseccionMaterialService) {
    this.dialogConfig = new MatDialogConfig();
    this.constantes = new CONSTANTES();
  }

  ngOnInit() {
    // Inicialización del formulario
    this.editarSBMForm = new FormBuilder().group({
      nombreSBM: [ this.sbmAEditar.nombre, [Validators.required, Validators.maxLength(30)]]
    });
  }

  /**
   * Get para el nombre de la subsección a editar
   */
  get nombreSBM() {
    return this.editarSBMForm.get('nombreSBM');
  }

  /**
   * Metodo para cerrar el modal
   */
  cerrarEditarSBMForm(): void {
    this.editarSBMForm.get('nombreSBM').reset();
    this.dialog.closeAll();
  }

  /**
   * Metodo principal para editar finalmente la sbm
   */
  editarSBM(): void {
    // se establece el nuevo nombre para la subsección material
    this.sbmAEditar.nombre = this.editarSBMForm.get('nombreSBM').value;
    // Se establece el grupo
    this.sbmAEditar.grupo = this.grupo;
    // se hace llamado al servicio para actualizar
    const request = this.sbmService.actualizarSubseccionMaterial(this.sbmAEditar).subscribe(
      response => {
        // se muestra el mensaje de éxito
        Swal.fire({
          title: '¡Éxito al actualizar!',
          text: response.mensaje,
          type: 'success',
          confirmButtonText: 'Aceptar'
        });
        // Se emite para actualizar
        this.sbmActualizarValueChange.emit({sbm: this.sbmAEditar})
        // se desubscribe
        request.unsubscribe();
      }
    );
    // Se cierra el modal
    this.dialog.closeAll();
  }

  /**
   * Metodo para mostrar el modal
   */
  abrirModalEditarSBM(): void {
    this.dialog.open(this.modalEditarSBM, this.dialogConfig);
  }

  /**
   * Metodo que permite validar si un usuario puede editar la información de un curso.
   * retorna verdadero o false según el caso
   */
  puedeEditarSBM(): boolean {
    return this.authService.validarTienePermisoEnAlgunPerfilDeInstitucion(
      this.constantes.EDITAR_SUBSECCION_MATERIAL_GRUPO.ID, this.grupo.curso.areaTematica.institucion.institucionPK.nombre);
  }

}
