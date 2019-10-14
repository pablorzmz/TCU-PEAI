import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../../../data/services/auth.service';
import {SubseccionMaterial} from '../../../../data/schema/SubseccionMaterial';

@Component({
  selector: 'app-editar-subseccion-material-grupo',
  templateUrl: './editar-subseccion-material-grupo.component.html',
  styleUrls: ['./editar-subseccion-material-grupo.component.css']
})
export class EditarSubseccionMaterialGrupoComponent implements OnInit {

  // La subsección a editar
  @Input() sbmAEditar: SubseccionMaterial;

  // Contiene la configuracion del dialog
  dialogConfig: MatDialogConfig;

  // Mapeo como atributo para vista del modal.
  @ViewChild( ('modalEditarSBM'), {static: false}) modalEditarSBM: TemplateRef<any>;

  // Form para controlar los campos
  editarSBMForm: FormGroup;

  constructor(private dialog: MatDialog, private authService: AuthService) {
    this.dialogConfig = new MatDialogConfig();
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

  }

  /**
   * Metodo para mostrar el modal
   */
  abrirModalEditarSBM(): void {
    this.dialog.open(this.modalEditarSBM, this.dialogConfig);
  }

}
