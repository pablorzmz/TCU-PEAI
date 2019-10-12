import { Component, OnInit } from '@angular/core';
import {Curso} from '../../../../data/schema/Curso';
import {CursoService} from '../../../../data/services/curso.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-listar-cursos',
  templateUrl: './listar-cursos.component.html',
  styleUrls: ['./listar-cursos.component.css']
})
export class ListarCursosComponent implements OnInit {
  cursos: Curso[]; // Lista de cursos para mostrar
  paginador: any; // Objeto donde se guarda el resultado de la consulta, que será enviado para la paginación
  rutaPag = '/cursos/page/'; // Ruta del paginador

  constructor(private cursoService: CursoService,
              private activatedRoute: ActivatedRoute) { }

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
      const request = this.cursoService.getCursos(page).subscribe(
        (response) => {
          // Si se reciben cursos se le asigna al atributo cursos la lista de ellos
          this.cursos = response.content as Curso[];
          // Se le asigna al atributo paginador el resultado completo de la consulta
          this.paginador = response;
          // Se elimina la subscripción
          request.unsubscribe();
        });
    });
  }

}
