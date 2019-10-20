import { Component, OnInit } from '@angular/core';
import {Institucion} from '../../../../data/schema/Institucion';
import {InstitucionService} from '../../../../data/services/institucion.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-instituciones',
  templateUrl: './instituciones.component.html',
  styleUrls: ['./instituciones.component.css']
})
export class InstitucionesComponent implements OnInit {
  instituciones: Institucion[]; // Lista de Instituciones para mostrar
  paginador: any; // Objeto donde se guarda el resultado de la consulta, que será enviado para la paginación
  rutaPag = '/instituciones/page/';

  constructor(public institucionService: InstitucionService,
              private activatedRoute: ActivatedRoute) {
    this.instituciones = new Array();
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe( params => {
      // Se obtiene la página actual que viene por parametro
      let page: number = +params.get('page');
      // Se pregunta si el parametro existe
      if (!page) {
        // Si no existe se pone en 0 (primera pagina)
        page = 0;
      }
      // Se realiza el Request al backend
      const request = this.institucionService.getInstituciones2(page).subscribe(
        (response) => {
          // Si se reciben instituciones se le asigna al atributo instituciones la lista de ellas
          this.instituciones = response.content as Institucion[];
          // Se le asigna al atributo paginador el resultado completo de la consulta
          this.paginador = response;
          // Se elimina la subscripción
          request.unsubscribe();
        });
    });
  }

}
