import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../auth.service';
import { Users } from '../model/users';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit {
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'role', 'password', 'acoes'];

  declare usuarios: Array<Users>;
  dataSource = new MatTableDataSource<Users>();

  horizontalPosition: MatSnackBarHorizontalPosition = 'right';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  constructor(
    private authService: AuthService,
    private router: Router,
    private _snackBar: MatSnackBar
    ) {
  }

  ngOnInit(): void {
    this.retornaTodosUsuariosCadastrados();
  }

  retornaTodosUsuariosCadastrados() {
    this.authService.listUsers().subscribe(
      (usuarios: Array<Users>) => {
        if(usuarios != null) {
          this.dataSource.data = usuarios;
        }
      },
      (erro) => {
        console.error('Erro ao retorna usuários:', erro);
      }
    );
  }

  // Método para obter classes com base nas permissões
  getBadgeClasses(perfil: string) {
    return {
      'badge-success': perfil === 'USER',
      'badge-danger':  perfil === 'ADMIN'
      // 'badge-primary': !this.temPermissao(this.permissaoUsuario) && !this.temPermissao(this.permissaoAdmin),
      // 'badge-info': this.temPermissao(this.permissaoUsuario) && this.temPermissao(this.permissaoAdmin)
    };
  }

  cadastrarUsuario() {

  }

  editarUsuario(id: Number) {
    this.router.navigate(['/atualizarUsuario', id]);
  }

  removerUsuario(id: Number) {
    this.authService.removerUsuario(id).subscribe(() => {
      this._snackBar.open('Usuário removido com sucesso!', 'Fechar', {
        panelClass: 'success-snackbar',
        duration: 3000,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });
      this.retornaTodosUsuariosCadastrados();
    });
  }

  cadastraNovosUsuarios() {
    this.router.navigate(['/cadastrarUsuario']);
  }

}
