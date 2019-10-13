import {Component, Input, OnInit} from '@angular/core';
import {GrupoService} from '../../../../data/services/grupo.service';
import {ActivatedRoute} from '@angular/router';
import {Grupo} from '../../../../data/schema/Grupo';
import {Curso} from '../../../../data/schema/Curso';
import {AuthService} from '../../../../data/services/auth.service';

@Component({
  selector: 'app-listar-grupos-curso',
  templateUrl: './listar-grupos-curso.component.html',
  styleUrls: ['./listar-grupos-curso.component.css']
})
export class ListarGruposCursoComponent implements OnInit {

  constructor(private route: ActivatedRoute, private grupoService: GrupoService, private authService: AuthService) { }

  @Input() curso: Curso;
  @Input() nombreInstitucion: string;
  grupos: Array<Grupo>;

  ngOnInit() {
      // Se solicitan las areas tematicas de la area-tematica
      const request = this.grupoService.getGruposCurso(this.curso.id).subscribe(
        res => {
          // Si las recibe se asignan al atributo areasTematicas
          this.grupos = res;
          // Quitamos la subscripcion
          request.unsubscribe();
        },
        // En caso de que no se reciba correctamente se lanza una excepcion
        // Esto se implementa cuando esté el interceptor
        err => console.error(err)
      );
  }
  actualizarGrupos($event): any {
      this.grupos.push($event.grupo);
  }
}
