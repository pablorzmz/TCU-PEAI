import {Component, Inject, Input, OnInit} from '@angular/core';
import { InstitucionService } from '../../../../data/services/institucion.service';
import {AreaTematica} from '../../../../data/schema/AreaTematica';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-listar-areas-tematicas',
  templateUrl: './listar-areas-tematicas.component.html',
  styleUrls: ['./listar-areas-tematicas.component.css']
})
export class ListarAreasTematicasComponent implements OnInit {

  constructor(private route: ActivatedRoute, private institucionService: InstitucionService) { }

  nombreInstitucion: string;             // Contiene el nombre de la institucion de la cual se espera obtener las areas tematicas
  areasTematicas: Array<AreaTematica>;   // Contiene las áreas temáticas de institucón

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      // Se solicita el parametro
      this.nombreInstitucion = params.get('nombreInstitucion');
      // Se solicitan las areas tematicas de la institucion
      const request = this.institucionService.getAreasTematicas(this.nombreInstitucion).subscribe(
        res => {
          // Si las recibe se asignan al atributo areasTematicas
          this.areasTematicas = res;
          console.log(this.areasTematicas);
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
