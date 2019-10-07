import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'paginator-nav',
  templateUrl: './paginator.component.html'
})
export class PaginatorComponent implements OnInit {
  // Objeto donde se guarda el resultado de la consulta, del cual se obtienen los datos necesarios para la paginacion
  @Input() paginador: any;
  paginas: number[]; // Lista de las paginas posibles
  @Input() ruta: string; // ruta que seguirá los enlaces

  constructor() { }

  ngOnInit() {
    this.paginas = new Array(this.paginador.totalPages).fill(0).map((valor, indice) => indice + 1);
  }

}
