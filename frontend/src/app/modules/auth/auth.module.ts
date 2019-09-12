import { NgModule } from '@angular/core';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AboutComponent } from './components/about/about.component';


@NgModule({
  declarations: [LoginComponent, RegisterComponent, AboutComponent],
  imports: [
    AuthRoutingModule
  ]
})
export class AuthModule { }
