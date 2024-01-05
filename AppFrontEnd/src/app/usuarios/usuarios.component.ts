import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../auth.service';
import { Users } from '../model/users';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit {
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'password'];
  declare usuarios: Array<Users>;
  dataSource = new MatTableDataSource<Users>();

  constructor(private authService: AuthService) {

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

}
