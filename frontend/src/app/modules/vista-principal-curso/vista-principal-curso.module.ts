import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';


import {VistaPrincipalCursoComponent} from './pages/principal/vista-principal-curso.component';
import {VistaPrincipalCursoRoutingModule} from './vista-principal-curso-routing.module';


@NgModule({
  declarations: [
    VistaPrincipalCursoComponent
  ],
  exports: [
    VistaPrincipalCursoComponent
  ],
  imports: [
    CommonModule, VistaPrincipalCursoRoutingModule
  ]
})
export class VistaPrincipalCursoModule {}
