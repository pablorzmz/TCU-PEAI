import { NgModule } from '@angular/core';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AboutComponent } from './components/about/about.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HeaderComponent } from './components/header/header.component';
import {CommonModule} from '@angular/common';
import {MatIconModule, MatMenuModule} from '@angular/material';


@NgModule({
  declarations: [LoginComponent, RegisterComponent, AboutComponent, HeaderComponent],
  exports: [
    HeaderComponent
  ],
  imports: [
    AuthRoutingModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    MatMenuModule,
    MatIconModule
  ]
})
export class AuthModule { }
