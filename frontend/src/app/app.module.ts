import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { AuthModule } from './modules/auth/auth.module';
import { AreaTematicaModule } from './modules/area-tematica/area-tematica.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    AreaTematicaModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
