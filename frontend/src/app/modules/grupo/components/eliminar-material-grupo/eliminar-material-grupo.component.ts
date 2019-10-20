import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MaterialPK} from '../../../../data/schema/MaterialPK';
import {AuthService} from '../../../../data/services/auth.service';
import {MaterialService} from '../../../../data/services/material.service';
import {CONSTANTES} from '../../../../data/util/Constantes';
import {Grupo} from '../../../../data/schema/Grupo';
import Swal from "sweetalert2";
import {SubseccionMaterial} from '../../../../data/schema/SubseccionMaterial';

@Component({
  selector: 'app-eliminar-material-grupo',
  templateUrl: './eliminar-material-grupo.component.html',
  styleUrls: ['./eliminar-material-grupo.component.css']
})
export class EliminarMaterialGrupoComponent implements OnInit {

  // Id para eliminar el material
  @Input() materialPk: MaterialPK;

  // Nombre de la institucion del curso
  @Input() grupo: Grupo;

  // Aqui se emite el evento para eliminar la subsección de la vista
  @Output() materialEliminarValueChange = new EventEmitter<{eMaterialPk: MaterialPK}>();

  // Acceso a constantes
  constantes: CONSTANTES;

  constructor(private authService: AuthService, private materialService: MaterialService) {
    this.constantes = new CONSTANTES();
  }

  ngOnInit() {
  }


  /**
   * Método que valida si se puede o no eliminar un material
   */
  puedeEliminarMaterial(): boolean {
    return this.authService.validarTienePermisoEnAlgunPerfilDeInstitucion(
      this.constantes.ELIMINAR_SUBSECCION_MATERIAL_GRUPO.ID, this.grupo.curso.areaTematica.institucion.institucionPK.nombre);
  }

  /**
   * Método que permite eliminar el material. Hace la comunicaicón al servido con el servicio
   */
  eliminarMaterial(): void {
    Swal.fire({
      title: '¿Seguro desea eliminar?',
      // tslint:disable-next-line:max-line-length
      text: `¿Desea elminar el material \"${this.materialPk.nombre}\" de manera permanente? Esto implica que se eliminará los comentarios sobre el material`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Volver'
    }).then((result) => {
      if (result.value) {
        const request = this.materialService.eliminarMaterial(this.materialPk).subscribe(
          response => {
            // se muestra el mensaje de éxito
            Swal.fire({
              title: '¡Éxito al eliminar!',
              text: response.mensaje,
              type: 'success',
              confirmButtonText: 'Aceptar'
            });
            // se emite el cambio
            this.materialEliminarValueChange.emit({ eMaterialPk: this.materialPk});
            // se desubscribe
            request.unsubscribe();
          }
        );
      }
    });
  }

}
