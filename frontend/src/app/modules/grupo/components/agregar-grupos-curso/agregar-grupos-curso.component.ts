import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {Curso} from '../../../../data/schema/Curso';
import {Usuario} from '../../../../data/schema/Usuario';
import {AuthService} from '../../../../data/services/auth.service';

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

  // Usuario actual del sistema
  usuario: Usuario;

  // PopUp para mostrar la interfaz de carga de taxi
  @ViewChild('modalAgregarGrupo', {static: false}) modalCargarArchivo: TemplateRef<any>;

  constructor(private dialog: MatDialog, private authService: AuthService) {
    this.dialogConfig = new MatDialogConfig();
  }

  ngOnInit() {
    // Tomamos el nombre del usuario actual
    this.usuario = this.authService.usuario;
  }

  /**
   * Metodo que abre el pop up del analisis de archivos.
   */
  abiriAgregarGrupo() {
    this.dialog.open(this.modalCargarArchivo, this.dialogConfig);
  }

}
