import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PaginatorComponent} from './paginator/paginator.component';
import {CommonsRoutingModule} from './commons-routing.module';

@NgModule({
  declarations: [
    PaginatorComponent
  ],
  exports: [
    PaginatorComponent
  ],
  imports: [
    CommonModule,
    CommonsRoutingModule
  ]
})
export class CommonsModule { }
