import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SubseccionMaterial} from '../../../../data/schema/SubseccionMaterial';
import {CONSTANTES} from '../../../../data/util/Constantes';
import {AuthService} from '../../../../data/services/auth.service';
import Swal from 'sweetalert2';
import {SubseccionMaterialService} from '../../../../data/services/subseccion-material.service';

@Component({
  selector: 'app-eliminar-subseccion-material-grupo',
  templateUrl: './eliminar-subseccion-material-grupo.component.html',
  styleUrls: ['./eliminar-subseccion-material-grupo.component.css']
})
export class EliminarSubseccionMaterialGrupoComponent implements OnInit {

  // subsección de material para eliminar
  @Input() sbm: SubseccionMaterial;

  // nombre de la institucion
  @Input()  private nombreInstitucion: string;

  // Aqui se emite el evento para eliminar la subsección de la vista
  @Output() sbmEliminarValueChange = new EventEmitter<{sbm: SubseccionMaterial}>();

  // acceso a las constantes
  private constantes: CONSTANTES;

  constructor( private authService: AuthService, private sbmService: SubseccionMaterialService ) {
    this.constantes = new CONSTANTES();
  }

  ngOnInit() {
  }

  /**
   * Método que permite eliminar la subsección material
   */
  eliminarSubseccionMaterial(): void {
    // si el usuario tiene permiso
    if (this.puedeEliminarSubseccionMaterial()) {
      // se pregunta
      Swal.fire({
        title: '¿Seguro desea eliminar?',
        text: `¿Desea elminar la subsección de material \"${this.sbm.nombre}\" de manera permanente?`,
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Volver'
      }).then((result) => {
        if (result.value) {
          // se elimina utlizando el servicio
          const  request =  this.sbmService.eliminarSubseccionMaterial(this.sbm).subscribe(
            response => {
              // se muestra mensaje
              Swal.fire({
                title: '¡Éxito al eliminar!',
                text: response.mensaje,
                type: 'success',
                confirmButtonText: 'Aceptar'
              });
              // se emite
              this.sbmEliminarValueChange.emit({ sbm: this.sbm } );
              // se desubscribe
              request.unsubscribe();
            }
          );
        }
      });
    } else {
      // mostrar error de permisos
      Swal.fire({
        title: 'Acesso no autorizado',
        text: 'No  tiene permisos para realizar eliminar una subsección de material',
        type: 'error',
        confirmButtonText: 'Aceptar'
      });
    }
  }

  /**
   * Metodo que verifica si tiene el permiso para eliminar el material
   */
  puedeEliminarSubseccionMaterial(): boolean {
    return this.authService.validarTienePermisoEnAlgunPerfilDeInstitucion(
      this.constantes.ELIMINAR_SUBSECCION_MATERIAL_GRUPO.ID, this.nombreInstitucion);
  }
}
