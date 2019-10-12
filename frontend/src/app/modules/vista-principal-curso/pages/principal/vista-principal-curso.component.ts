import {Component, Input, OnInit} from '@angular/core';
import {Curso} from '../../../../data/schema/Curso';
import {ActivatedRoute} from '@angular/router';
import {VistaPrincipalCursoService} from '../../../../data/services/vista-principal-curso.service';
import {Router} from '@angular/router';
import {SubseccionMaterial} from '../../../../data/schema/SubseccionMaterial';


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
  private listaSubseccionMateriales: any;

  constructor(
      private activatedRoute: ActivatedRoute,
      private vistaPrincipalService: VistaPrincipalCursoService,
      private  router: Router
    ) {
    // Datos dummies mientras incializa
    this.listaSubseccionMateriales = null;
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
            // Se obtienen todas las subsecciones de materiales disponibles
            this.listaSubseccionMateriales = Array<SubseccionMaterial>();
            const idsGrupos = Object.keys(response.subseccionesMaterialPorGrupo);
            idsGrupos.map(
              // Se recorre por cada id de grupo las subsecciones de materiales
              id => { response.subseccionesMaterialPorGrupo[id].map(
                  // se agrega al array
                  sbm => { this.listaSubseccionMateriales.push(sbm as SubseccionMaterial);}
                );
              }
            );
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
