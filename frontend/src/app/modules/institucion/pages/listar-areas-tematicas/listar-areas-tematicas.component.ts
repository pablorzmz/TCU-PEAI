import {Component, Inject, OnInit} from '@angular/core';
import { InstitucionService } from '../../../../data/services/institucion.service';
import {AreaTematica} from '../../../../data/schema/AreaTematica';

@Component({
  selector: 'app-listar-areas-tematicas',
  templateUrl: './listar-areas-tematicas.component.html',
  styleUrls: ['./listar-areas-tematicas.component.css']
})
export class ListarAreasTematicasComponent implements OnInit {

  constructor(private institucionService: InstitucionService) { }
  areasTematicas: Array<AreaTematica>;

  ngOnInit() {
    this.institucionService.getAreasTematicas('Universidad de Costa Rica').subscribe(
      res => {
        this.areasTematicas = res;
        console.log(this.areasTematicas);
      },
      err => console.error(err)
    );
  }

}
