import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Livros } from '../model/livros';
import { LivroService } from '../service/livro.service';
import { DialogLivrosComponent } from './dialog-livros/dialog-livros.component';
import { MatSnackBar, MatSnackBarConfig, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-livros',
  templateUrl: './livros.component.html',
  styleUrls: ['./livros.component.scss']
})
export class LivrosComponent {
  displayedColumns: string[] = ['titulo', 'autor', 'genero', 'editora', 'classificacao', 'acoes'];
  declare livros: Array<Livros>;
  dataSource = new MatTableDataSource<Livros>();
  mensagem: boolean = false;

  horizontalPosition: MatSnackBarHorizontalPosition = 'right';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  constructor(
    private livroService: LivroService,
    public dialog: MatDialog,
    private router: Router,
    private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.retornaTodosLivrosCadastrados();
  }

  retornaTodosLivrosCadastrados() {
    this.livroService.livrosCadastrados().subscribe(
      (livros: Array<Livros>) => {
        if(livros != null) {
          this.dataSource.data = livros;
        }
      },
      (erro) => {
        console.error('Erro ao retorna livros:', erro);
      }
    );
  }

  visualizarLivro(id: Number) {
    const dialogRef = this.dialog.open(DialogLivrosComponent, {
      data: { livroId: id},
      width: '350px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  editarLivro(id: Number) {
    this.router.navigate(['/atualizarLivro', id]);
  }

  removerLivro(id: Number) {
    this.livroService.removerLivro(id).subscribe(() => {
      this._snackBar.open('Livro removido com sucesso!', 'Fechar', {
        panelClass: ['red-snackbar'],
        duration: 3000,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });
      this.retornaTodosLivrosCadastrados();
    });
  }
}
