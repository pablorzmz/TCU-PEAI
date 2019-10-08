import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';


import {VistaPrincipalCursoComponent} from './pages/principal/vista-principal-curso.component';
import {VistaPrincipalCursoRoutingModule} from './vista-principal-curso-routing.module';
import {GrupoModule} from '../grupo/grupo.module';


@NgModule({
  declarations: [
    VistaPrincipalCursoComponent
  ],
  exports: [
    VistaPrincipalCursoComponent
  ],
  imports: [
    CommonModule, VistaPrincipalCursoRoutingModule, GrupoModule
  ]
})
export class VistaPrincipalCursoModule {}
