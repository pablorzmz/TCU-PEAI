import { NgModule } from '@angular/core';
import { AboutComponent } from './pages/about/about.component';
import { AboutRoutingModule } from './about-routing.module';

@NgModule({
  declarations: [AboutComponent],
  exports: [
    AboutComponent
  ],
  imports: [
    AboutRoutingModule
  ]
})
export class AboutModule { }
