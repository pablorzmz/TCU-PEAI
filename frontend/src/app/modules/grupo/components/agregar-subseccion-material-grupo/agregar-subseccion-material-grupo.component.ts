import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {Grupo} from '../../../../data/schema/Grupo';
import {AuthService} from '../../../../data/services/auth.service';
import {CONSTANTES} from '../../../../data/util/Constantes';

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

  // Form para controlar los campos
  agregarSubseccionMaterialForm: FormGroup;

  // accesso a constante
  private constantes: CONSTANTES;

  constructor( private dialog: MatDialog, private authService: AuthService ) {
    this.dialogConfig = new MatDialogConfig();
    this.constantes = new CONSTANTES();
  }

  ngOnInit() {
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
    console.log(this.grupo);
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
