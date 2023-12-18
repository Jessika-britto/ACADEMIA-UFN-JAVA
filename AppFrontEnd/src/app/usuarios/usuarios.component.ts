import { Usuarios } from './../model/usuarios';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { UsuariosService } from '../service/usuarios.service';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit {
  displayedColumns: string[] = ['nome', 'email', 'senha'];
  declare usuarios: Array<Usuarios>;
  dataSource = new MatTableDataSource<Usuarios>();

  constructor(private usuariosService: UsuariosService) {

  }

  ngOnInit(): void {
    this.retornaTodosUsuariosCadastrados();
  }

  retornaTodosUsuariosCadastrados() {
    this.usuariosService.usuariosCadastrados().subscribe(
      (usuarios: Array<Usuarios>) => {
        if(usuarios != null) {
          console.error('Retorna usuários:', usuarios);
          this.dataSource.data = usuarios;
        }
      },
      (erro) => {
        console.error('Erro ao retorna usuários:', erro);
      }
    );
  }

}
