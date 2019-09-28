import {Component, Inject, Input, OnInit} from '@angular/core';
import { InstitucionService } from '../../../../data/services/institucion.service';
import {AreaTematica} from '../../../../data/schema/AreaTematica';
import {InstitucionPK} from '../../../../data/schema/InstitucionPK';

@Component({
  selector: 'app-listar-areas-tematicas',
  templateUrl: './listar-areas-tematicas.component.html',
  styleUrls: ['./listar-areas-tematicas.component.css']
})
export class ListarAreasTematicasComponent implements OnInit {

  constructor(private institucionService: InstitucionService) { }

  @Input() institucion: InstitucionPK;   // Se refiere a la institucion en la cual se encuentra
  areasTematicas: Array<AreaTematica>;   // Contiene las áreas temáticas de institucón

  ngOnInit() {
    // const nombreInstitucion = this.institucion.getNombre();
    const nombreInstitucion = 'Cedes Don Bosco';
    // Se solicitan las areas tematicas de la institucion
    const request = this.institucionService.getAreasTematicas(nombreInstitucion).subscribe(
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
  }

}
