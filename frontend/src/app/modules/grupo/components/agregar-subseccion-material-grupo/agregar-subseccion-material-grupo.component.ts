import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {Grupo} from '../../../../data/schema/Grupo';
import {AuthService} from '../../../../data/services/auth.service';
import {CONSTANTES} from '../../../../data/util/Constantes';
import {SubseccionMaterial} from '../../../../data/schema/SubseccionMaterial';
import {SubseccionMaterialService} from '../../../../data/services/subseccion-material.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-agregar-subseccion-material-grupo',
  templateUrl: './agregar-subseccion-material-grupo.component.html',
  styleUrls: ['./agregar-subseccion-material-grupo.component.css']
})
export class AgregarSubseccionMaterialGrupoComponent implements OnInit {

  // Grupo para el cual se van a crear la nueva subsección de material
  @Input() grupo: Grupo;

  // Contiene la configuracion del dialog
  dialogConfig: MatDialogConfig;

  // Mapeo como atributo para vista del modal.
  @ViewChild('modalAgregarSubseccionMaterial', {static: false}) modalAgregarSubseccionMaterial: TemplateRef<any>;

  // Aqui se emite el evento de que se recibio una nueva subsección de material (sbm)
  @Output() sbmValueChange = new EventEmitter<{sbm: SubseccionMaterial}>();

  // Form para controlar los campos
  agregarSubseccionMaterialForm: FormGroup;

  // accesso a constante
  private constantes: CONSTANTES;

  constructor( private dialog: MatDialog, public authService: AuthService, public subseccionMaterialService: SubseccionMaterialService) {
    this.dialogConfig = new MatDialogConfig();
    this.constantes = new CONSTANTES();
  }

  ngOnInit() {
    // Inicialización del formulario
    this.agregarSubseccionMaterialForm = new FormBuilder().group({
        nombreSubseccion: [null, [Validators.required, Validators.maxLength(30)]]
    });
  }


  /**
   * Metodo que permite abrir el modal para agregar un material
   */
  abrirAgregarSBM(): void {
    this.agregarSubseccionMaterialForm.get('nombreSubseccion').reset();
    this.dialog.open(this.modalAgregarSubseccionMaterial, this.dialogConfig);
  }

  /**
   * Metodo para cerar y reiniciar el campo para el nombre de la subsección.
   */
  cerrarAgregarSBM(): void {
    this.agregarSubseccionMaterialForm.get('nombreSubseccion').reset();
    this.dialog.closeAll();
  }

  /**
   * Get para el campo a validar
   */
  get nombreSubseccion() {
    return this.agregarSubseccionMaterialForm.get('nombreSubseccion');
  }

  /**
   * Metodo que realiza la creación de la nueva subsección de material
   */
  crearSubseccionMaterial(): void {
    // Si el usuario tiene permiso para hacerlo
    if (this.puedeAgregarSubseccionMaterial()) {
      // Se crea una nueva subsección de material
      const subseccionACrear = new SubseccionMaterial();
      subseccionACrear.habilitada = true;
      subseccionACrear.grupo = this.grupo;
      subseccionACrear.nombre = this.nombreSubseccion.value;
      // finalmente se hace un request y se suscribe al servicio
      const request = this.subseccionMaterialService.crearNuevaSubseccionMaterial(subseccionACrear).subscribe(
        response => {
          // se emite el nuevo valor
          const sbmRecibida: SubseccionMaterial = response.nuevaSBM as SubseccionMaterial;
          this.sbmValueChange.emit({ sbm: sbmRecibida } );
          // se dispara el mensaje
          Swal.fire( '¡Éxtio al agregar!', response.mensaje , 'success');
          // se cierra el modal
          this.cerrarAgregarSBM();
          // desubscribirse
          request.unsubscribe();
        }
      );
    } else {
      // mostrar mensaje de acceso no autorizado
      Swal.fire( 'Accesso no autorizado', 'No tiene permisos para realizar esta acción.' , 'error');
    }
  }

  /**
   * Metodo que realiza la validación para ver sise puede o no agregar una subsección material
   * retonra: verdadero o falso segun el caso
   */
  puedeAgregarSubseccionMaterial(): boolean {
    return this.authService.validarTienePermisoEnAlgunPerfilDeInstitucion(this.constantes.AGREGAR_SUBSECCION_MATERIAL_GRUPO.ID,
      this.grupo.curso.areaTematica.institucion.institucionPK.nombre);
  }

}
