import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {AreaTematicaService} from '../../../../data/services/area-tematica.service';
import {AreaTematica} from '../../../../data/schema/AreaTematica';

@Component({
  selector: 'app-listar-areas-tematicas',
  templateUrl: './listar-areas-tematicas.component.html',
  styleUrls: ['./listar-areas-tematicas.component.css']
})
export class ListarAreasTematicasComponent implements OnInit {

  constructor(private route: ActivatedRoute, private areaTematicaService: AreaTematicaService) { }

  areasTematicas: AreaTematica[];   // Contiene las áreas temáticas de institucón

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      // Se solicitan las areas tematicas de la area-tematica
      const request = this.areaTematicaService.getAreasTematicas().subscribe(
        res => {
          // Si las recibe se asignan al atributo areasTematicas
          this.areasTematicas = res as AreaTematica[];
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
