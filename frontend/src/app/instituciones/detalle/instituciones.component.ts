import { Component, OnInit } from '@angular/core';
import {Institucion} from '../../data/schema/Institucion';
import {InstitucionService} from './institucion.service';

@Component({
  selector: 'app-instituciones',
  templateUrl: './instituciones.component.html',
  styleUrls: ['./instituciones.component.css']
})
export class InstitucionesComponent implements OnInit {
  instituciones: Institucion[];
  // instituciones2: string[];

  constructor(private institucionService: InstitucionService) { }

  ngOnInit() {
    this.institucionService.getInstituciones().subscribe(
      institucion => this.instituciones = institucion
    );
  }

}
