import {Component, Input, OnInit} from '@angular/core';
import {Curso} from '../../../../data/schema/Curso';
import {AreaTematica} from '../../../../data/schema/AreaTematica';
import {Institucion} from '../../../../data/schema/Institucion';
import {Usuario} from '../../../../data/schema/Usuario';
import {InstitucionPK} from '../../../../data/schema/InstitucionPK';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-vista-principal-curso',
  templateUrl: './vista-principal-curso.component.html',
  styleUrls: ['./vista-principal-curso.component.css']
})
export class VistaPrincipalCursoComponent implements OnInit {

  private curso: Curso;
  private areaTematica: AreaTematica;
  private institucion: Institucion;
  private profesorCurso: Usuario;

  constructor(private activatedRoute: ActivatedRoute) {
    this.profesorCurso = new Usuario();
    this.profesorCurso.nombre = 'Un nombre de profesor valido';
    this.curso = new Curso();
    this.curso.nombre = 'Un nombre de cuso valido';
    this.areaTematica = new AreaTematica();
    this.areaTematica.nombre = 'Un nombre de area tematica valida';
    this.institucion = new Institucion();
    this.institucion.institucionPK = new InstitucionPK();
    this.institucion.institucionPK.nombre = 'Un nombre de institucion valido';
    this.curso.descripcion = 'Una descripcionValida';
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
        // Llamar al servicio y obtener lo que se ocupa del curso
      }
    ).unsubscribe();
  }

}
