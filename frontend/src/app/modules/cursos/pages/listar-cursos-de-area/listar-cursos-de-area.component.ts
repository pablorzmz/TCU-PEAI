import { Component, OnInit } from '@angular/core';
import {CursoService} from '../../../../data/services/curso.service';
import {ActivatedRoute} from '@angular/router';
import {Curso} from '../../../../data/schema/Curso';
import {CONSTANTES} from "../../../../data/util/Constantes";
import {AuthService} from "../../../../data/services/auth.service";

@Component({
  selector: 'app-listar-cursos-de-area',
  templateUrl: './listar-cursos-de-area.component.html',
  styleUrls: ['./listar-cursos-de-area.component.css']
})
export class ListarCursosDeAreaComponent implements OnInit {
  cursos: Curso[]; // Lista de cursos para mostrar
  idArea: number; // id del area de la cual quiero la lista de cursos

  // Se obtienen las contantes de permisos
  constantes = new CONSTANTES();

  constructor(private cursoService: CursoService,
              private activatedRoute: ActivatedRoute,
              private authService: AuthService) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe( params => {
      // Se obtiene el id del Area
      this.idArea = +params.get('area');
      // Se realiza el Request al backend
      const request = this.cursoService.getCursosDeArea(this.idArea).subscribe(
        (response) => {
          // Si se reciben cursos se le asigna al atributo cursos la lista de ellos
          this.cursos = response as Curso[];
          // Se elimina la subscripción
          request.unsubscribe();
        });
    });
  }

  actualizarCursos($event): any {
    this.cursos.push($event.curso);
  }

}
