import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { AuthModule } from './modules/auth/auth.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AreaTematicaModule } from './modules/area-tematica/area-tematica.module';
import {InstitucionModule} from './modules/instituciones/institucion.module';
import { VistaPrincipalCursoModule} from './modules/vista-principal-curso/vista-principal-curso.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    BrowserAnimationsModule,
    AreaTematicaModule,
    InstitucionModule,
    VistaPrincipalCursoModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
