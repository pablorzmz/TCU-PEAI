import { Component, OnInit } from '@angular/core';
import {Institucion} from '../../data/schema/Institucion';
import {InstitucionService} from './institucion.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-instituciones',
  templateUrl: './instituciones.component.html',
  styleUrls: ['./instituciones.component.css']
})
export class InstitucionesComponent implements OnInit {
  instituciones: Institucion[];
  paginador: any;

  constructor(private institucionService: InstitucionService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe( params => {
      let page: number = +params.get('page');
      if (!page) {
        page = 0;
      }
      this.institucionService.getInstituciones2(page).subscribe(
        (response) => {
          this.instituciones = response.content as Institucion[];
          this.paginador = response;
        });
    });
  }

}
