import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Livros } from 'src/app/model/livros';
import { LivroService } from 'src/app/service/livro.service';

@Component({
  selector: 'app-dialog-livros',
  templateUrl: './dialog-livros.component.html',
  styleUrls: ['./dialog-livros.component.scss']
})
export class DialogLivrosComponent implements OnInit {
  declare livro: Livros;

  constructor(@Inject(MAT_DIALOG_DATA) public data: { livroId: number },
  private livroService: LivroService) {}

  ngOnInit(): void {
    this.obterLivro();
  }

  obterLivro() {
     if (this.data.livroId != null) {
      console.log('Visualizar Livro Resposta: ', this.data.livroId);
      this.livroService.obterLivroCadastrado(this.data.livroId).subscribe((livro: Livros) => {
        console.log('Visualizar Livro Resposta: ', livro);
        this.livro = livro;
      });
    }
  }
}
