import {Component, Input, OnInit} from '@angular/core';
import {Curso} from '../../../../data/schema/Curso';
import {ActivatedRoute} from '@angular/router';
import {VistaPrincipalCursoService} from '../../../../data/services/vista-principal-curso.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-vista-principal-curso',
  templateUrl: './vista-principal-curso.component.html',
  styleUrls: ['./vista-principal-curso.component.css']
})
export class VistaPrincipalCursoComponent implements OnInit {

  private curso: Curso;
  private nombreAreaTematica: string;
  private nombreInstitucion: string;
  private nombreProfesorCurso: string;

  constructor(
      private activatedRoute: ActivatedRoute,
      private vistaPrincipalService: VistaPrincipalCursoService,
      private  router: Router
    ) {
    // Datos dummies mientras incializa
    this.curso = new Curso();
    this.curso.nombre = 'Cargando...';
    this.curso.descripcion = 'Cargando...';
    this.nombreProfesorCurso = 'Cargando...';
    this.nombreAreaTematica = 'Cargando...';
    this.nombreInstitucion = 'Cargando...';
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
        // Llamar al servicio y obtener lo que se ocupa del curso
        const idCursoParametro = +params.get('id');
        // Ahora se hace llamado al servicio para obtener los datos
        const request = this.vistaPrincipalService.getInformacionBasicaCurso(idCursoParametro).subscribe(
          response => {
            // Se asignan los valores recuperados del backend
            this.curso = response.curso;
            this.nombreAreaTematica = response.areaTematica;
            this.nombreInstitucion = response.nombreInstitucion;
            this.nombreProfesorCurso = response.profesorImparte;
            request.unsubscribe();
          },
          error => {
            // Redirigir en caso de error a arear tematicas
            this.router.navigate(['/areas_tematicas']);
          }
        );
      }
    );
  }

}
