import {Component, Inject, Input, OnInit} from '@angular/core';
import { AreaTematicaService } from '../../../../data/services/area-tematica.service';
import { ActivatedRoute } from '@angular/router';
import { AreaTematica } from '../../../../data/schema/AreaTematica';

@Component({
  selector: 'app-listar-areas-tematicas-institucion',
  templateUrl: './listar-areas-tematicas-institucion.component.html',
  styleUrls: ['./listar-areas-tematicas-institucion.component.css']
})
export class ListarAreasTematicasInstitucionComponent implements OnInit {

  constructor(private route: ActivatedRoute, public areaTematicaService: AreaTematicaService) { }

  nombreInstitucion: string;             // Contiene el nombre de la area-tematica de la cual se espera obtener las areas tematicas
  areasTematicas: AreaTematica[];   // Contiene las áreas temáticas de institucón

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      // Se solicita el parametro
      this.nombreInstitucion = params.get('nombreInstitucion');
      // Se solicitan las areas tematicas de la area-tematica
      const request = this.areaTematicaService.getAreasTematicasInstitucion(this.nombreInstitucion).subscribe(
        res => {
          // Si las recibe se asignan al atributo areasTematicas
          this.areasTematicas = res as AreaTematica[];
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
