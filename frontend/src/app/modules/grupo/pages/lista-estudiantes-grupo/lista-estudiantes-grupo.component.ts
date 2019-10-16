import {Component, Input, OnInit} from '@angular/core';
import {Usuario} from '../../../../data/schema/Usuario';
import {ActivatedRoute} from '@angular/router';
import {InstitucionPerfilUsuarioService} from '../../../../data/services/institucion-perfil-usuario.service';
import {UsuarioGrupoInscritoService} from '../../../../data/services/usuario-grupo-inscrito.service';
import {GrupoService} from '../../../../data/services/grupo.service';
import {Grupo} from '../../../../data/schema/Grupo';
import {GrupoPK} from '../../../../data/schema/GrupoPK';
import {UsuarioPK} from '../../../../data/schema/UsuarioPK';
import {UsuarioGrupoInscritoPK} from '../../../../data/schema/UsuarioGrupoInscritoPK';

@Component({
  selector: 'app-agregar-estudiantes-grupo',
  templateUrl: './lista-estudiantes-grupo.component.html',
  styleUrls: ['./lista-estudiantes-grupo.component.css']
})
export class ListaEstudiantesGrupoComponent implements OnInit {
  // Lista de estudiantes inscritos
  estudiantes: Usuario[];

  // Lista de estudiantes disponibles
  estudiantesDisponibles: Usuario[];

  // Lista de grupos
  grupos: Grupo[];

  // nombre de la institución del curso
  nombreInstitucion: string;

  // Id del Grupo
  grupoPK: GrupoPK;


  constructor(private activatedRoute: ActivatedRoute,
              private institucionPerfilUsuarioService: InstitucionPerfilUsuarioService,
              private usuarioGrupoInscritoService: UsuarioGrupoInscritoService,
              private grupoService: GrupoService) {
    // Inicializamos el grupoPK
    this.grupoPK = new GrupoPK();
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe( params => {
      // Se obtiene el nombre de la institucion
      this.nombreInstitucion = params.get('institucion');
      // Se obtiene el id del curso
      this.grupoPK.curso = +params.get('curso');
      // Se obtiene el numero de grupo
      this.grupoPK.numero = +params.get('numGrupo') ;
      // Se obtinen el periodo de tiempo
      this.grupoPK.periodoTiempo = params.get('periodoT');
      // Se realiza el Request al backend
      // tslint:disable-next-line:max-line-length
      const request = this.institucionPerfilUsuarioService.getEstudiantesDeInstitucion(this.nombreInstitucion, this.grupoPK.curso).subscribe(
        (response) => {
          // Se llena la lista con los estudiantes Disponibles
          this.estudiantesDisponibles = response as Usuario[];
          // Se elimina la subscripción
          request.unsubscribe();
        });
    });
    // tslint:disable-next-line:max-line-length
    const request2 = this.usuarioGrupoInscritoService.getEstudiantesDeGrupo(this.grupoPK.curso, this.grupoPK.numero, this.grupoPK.periodoTiempo).subscribe(
      (response) => {
        // Se llena la lista de estudiantes del grupo
        this.estudiantes = response as Usuario[];
        // Se elimina la subscripcion
        request2.unsubscribe();
      }
    );
  }

  agregarEstudiante(estudiante: Usuario): void {
    const usuarioPK: UsuarioPK = new UsuarioPK();
    usuarioPK.nombreUsuario = estudiante.usuarioPK.nombreUsuario;
    const usuarioGrupoInscritoPK: UsuarioGrupoInscritoPK = new UsuarioGrupoInscritoPK();
    usuarioGrupoInscritoPK.grupoPk = this.grupoPK;
    usuarioGrupoInscritoPK.usuarioPK = usuarioPK;
    const request = this.usuarioGrupoInscritoService.agregarEstudianteAGrupo(usuarioGrupoInscritoPK).subscribe(
      (response) => {
        // Obtenemos el usuario insertado
        const estudianteN = response as Usuario;
        // Se agrega el usuario a la lista de usuarios inscritos
        this.estudiantes.push(estudianteN);
        // Se elimina de la lista de usuarios disponibles
        this.estudiantesDisponibles.splice(this.estudiantesDisponibles.indexOf(estudiante), 1);
        request.unsubscribe();
      },
      (error) => {
        // Aqui va si hay algun error
      }
    );
  }

  eliminarEstudiante(estudiante: Usuario): void {
    const request = this.usuarioGrupoInscritoService.eliminarEstudianteDeGrupo(this.grupoPK, estudiante.usuarioPK.nombreUsuario).subscribe(
      (response) => {
        // Obtenemos el usuario insertado
        const estudianteN = response as Usuario;
        // Se agrega el usuario a la lista de usuarios inscritos
        this.estudiantesDisponibles.push(estudianteN);
        // Se elimina de la lista de usuarios disponibles
        this.estudiantes.splice(this.estudiantes.indexOf(estudiante), 1);
        request.unsubscribe();
      },
      (error) => {
        // Aqui va si hay algun error
      }
    );
  }
}
