import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { FeatherModule } from 'angular-feather';
import { allIcons } from 'angular-feather/icons';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DemoFlexyModule } from './demo-flexy-module';
import { FullComponent } from './layouts/full/full.component';

// Modules
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgApexchartsModule } from 'ng-apexcharts';
import { AuthInterceptorService } from './auth-interceptor.service';
import { CadastrarLivroComponent } from './cadastrar-livro/cadastrar-livro.component';
import { CadastrarUsuarioComponent } from './cadastrar-usuario/cadastrar-usuario.component';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { DialogLivrosComponent } from './livros/dialog-livros/dialog-livros.component';
import { LivrosComponent } from './livros/livros.component';
import { LoginComponent } from './login/login.component';
import { UsuariosComponent } from './usuarios/usuarios.component';

@NgModule({
  declarations: [
    AppComponent,
    FullComponent,
    LoginComponent,
    CadastrarComponent,
    UsuariosComponent,
    CadastrarLivroComponent,
    LivrosComponent,
    DialogLivrosComponent,
    CadastrarUsuarioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FeatherModule.pick(allIcons),
    DemoFlexyModule,
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
