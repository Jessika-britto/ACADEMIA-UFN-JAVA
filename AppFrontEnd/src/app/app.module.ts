import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FeatherModule } from 'angular-feather';
import { allIcons } from 'angular-feather/icons';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FullComponent } from './layouts/full/full.component';
import { DemoFlexyModule } from './demo-flexy-module'

// Modules
import { DashboardModule } from './dashboard/dashboard.module';
import { ComponentsModule } from './components/components.module';
import { LoginComponent } from './login/login.component';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { CommonModule } from '@angular/common';
import { NgApexchartsModule } from 'ng-apexcharts';
import { CadastrarLivroComponent } from './cadastrar-livro/cadastrar-livro.component';
import { LivrosComponent } from './livros/livros.component';
import { DialogLivrosComponent } from './livros/dialog-livros/dialog-livros.component';
import { AuthInterceptorService } from './auth-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    FullComponent,
    LoginComponent,
    CadastrarComponent,
    UsuariosComponent,
    CadastrarLivroComponent,
    LivrosComponent,
    DialogLivrosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FeatherModule.pick(allIcons),
    DemoFlexyModule,
    DashboardModule,
    ComponentsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
    DemoFlexyModule,
    NgApexchartsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
