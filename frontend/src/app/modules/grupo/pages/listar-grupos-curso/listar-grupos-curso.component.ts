import { Component, OnInit } from '@angular/core';
import {GrupoService} from '../../../../data/services/grupo.service';
import {ActivatedRoute} from '@angular/router';
import {Grupo} from '../../../../data/schema/Grupo';

@Component({
  selector: 'app-listar-grupos-curso',
  templateUrl: './listar-grupos-curso.component.html',
  styleUrls: ['./listar-grupos-curso.component.css']
})
export class ListarGruposCursoComponent implements OnInit {

  constructor(private route: ActivatedRoute, private grupoService: GrupoService) { }

  private idCurso: number;
  grupos: Array<Grupo>;

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      // Se solicita el parametro
      this.idCurso = Number(params.get('idCurso'));
      // Se solicitan las areas tematicas de la area-tematica
      const request = this.grupoService.getGruposCurso(this.idCurso).subscribe(
        res => {
          // Si las recibe se asignan al atributo areasTematicas
          this.grupos = res;
          console.log(this.grupos);
          // Quitamos la subscripcion
          request.unsubscribe();
        },
        // En caso de que no se reciba correctamente se lanza una excepcion
        // Esto se implementa cuando esté el interceptor
        err => console.error(err)
      );
    });
  }

}
