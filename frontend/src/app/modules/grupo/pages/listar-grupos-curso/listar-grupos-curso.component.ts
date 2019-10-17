import {Component, Input, OnInit} from '@angular/core';
import {GrupoService} from '../../../../data/services/grupo.service';
import {ActivatedRoute} from '@angular/router';
import {Grupo} from '../../../../data/schema/Grupo';
import {Curso} from '../../../../data/schema/Curso';
import {AuthService} from '../../../../data/services/auth.service';
import {CONSTANTES} from '../../../../data/util/Constantes';
import {SubseccionMaterial} from '../../../../data/schema/SubseccionMaterial';
import {MaterialService} from '../../../../data/services/material.service';

@Component({
  selector: 'app-listar-grupos-curso',
  templateUrl: './listar-grupos-curso.component.html',
  styleUrls: ['./listar-grupos-curso.component.css']
})
export class ListarGruposCursoComponent implements OnInit {

  constructor(private route: ActivatedRoute, private grupoService: GrupoService, private authService: AuthService, private materialService: MaterialService) { }

  // Se agrega la lista de subsecciones de materiales para ajustar por grupo
  @Input() listaSubseccionMateriales: Array<SubseccionMaterial>;

  // Se recibe el curso del grupo
  @Input() curso: Curso;

  // Se recibe el nombre de la institución del curso
  @Input() nombreInstitucion: string;

  // Lista de grupos que s epueden observar
  grupos: Array<Grupo>;

  // Se obtienen las contantes de permisos
  constantes = new CONSTANTES();

  // lista de materiales de cada grupo
  listaMateriales: Array<any>;

  ngOnInit() {
      // Se solicitan las areas tematicas de la area-tematica
      const request = this.grupoService.getGruposCurso(this.curso.id).subscribe(
        res => {
          // Si las recibe se asignan al atributo areasTematicas
          this.grupos = res;
          // Quitamos la subscripcion
          request.unsubscribe();
        },
        // En caso de que no se reciba correctamente se lanza una excepcion
        // Esto se implementa cuando esté el interceptor
        err => console.error(err)
      );
      // se recuperan los materiales
      const resquestMateriales = this.materialService.obtenerMaterialesCurso(this.curso.id).subscribe(
        response => {
          // Se asignan a la lista de materiales
          this.listaMateriales = response.materiales;
          // Se dessubscribre
          resquestMateriales.unsubscribe();
      }
    );
  }
  actualizarGrupos($event): any {
      this.grupos.push($event.grupo);
  }

  crearSubseccionMaterial($event): any {
    this.listaSubseccionMateriales.push($event.sbm);
  }

  eliminarSubseccionMaterial($event): any {
    this.listaSubseccionMateriales = this.listaSubseccionMateriales.filter( item => {
      return item.id !== ($event.sbm as SubseccionMaterial).id ;
    });
  }

  actualizarSubseccionMaterial($event): any {
    this.listaSubseccionMateriales.map( sbm => {
      if (sbm.id === $event.sbm.id) {
        sbm.nombre = $event.sbm.nombre;
      }
    });
  }
}
