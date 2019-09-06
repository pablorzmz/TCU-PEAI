import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AboutModule} from './modules/about/about.module';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AboutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
