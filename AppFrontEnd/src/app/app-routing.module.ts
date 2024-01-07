import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { CadastrarLivroComponent } from './cadastrar-livro/cadastrar-livro.component';
import { CadastrarUsuarioComponent } from './cadastrar-usuario/cadastrar-usuario.component';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { FullComponent } from './layouts/full/full.component';
import { LivrosComponent } from './livros/livros.component';
import { LoginComponent } from './login/login.component';
import { UsuariosComponent } from './usuarios/usuarios.component';

const routes: Routes = [
  {path:"login", component:LoginComponent},
  {path:"cadastrar", component:CadastrarComponent},
  {
    path:"",
    component:FullComponent,
    canActivate: [AuthGuard],
    children: [
      {path:"", redirectTo:"/cadastrarLivro", pathMatch:"full"},
      {path:"usuarios", component: UsuariosComponent},
      {path:"cadastrarLivro", component: CadastrarLivroComponent},
      {path:"atualizarLivro/:id", component: CadastrarLivroComponent},
      {path:"cadastrarUsuario", component: CadastrarUsuarioComponent},
      {path:"atualizarUsuario/:id", component: CadastrarUsuarioComponent},
      {path:"livros", component: LivrosComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
