import { LivroService } from './../service/livro.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Livros } from '../model/livros';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cadastrar-livro',
  templateUrl: './cadastrar-livro.component.html',
  styleUrls: ['./cadastrar-livro.component.scss']
})
export class CadastrarLivroComponent implements OnInit {
  declare id: number;
  declare livroForm: FormGroup;
  mensagem: boolean = false;
  mensagemAtualizacao: boolean = false;

  constructor(private fb: FormBuilder, private livroService: LivroService,private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.inicializarFormulario();
    this.obterId();
  }

  inicializarFormulario() {
    this.livroForm = this.fb.group({
      titulo: ['', Validators.required],
      autor: ['', Validators.required],
      genero: ['', Validators.required],
      editora: ['', Validators.required],
      classificacao: ['', Validators.required]
    });
  }

  cadastraNovosLivros() {
    this.livroService.cadastrarLivros(this.livroForm.value).subscribe(
      (livrosAdicionado: Livros) => {
        if(livrosAdicionado != null) {
            this.mensagem = true;
        }
        this.resetarFormulario();
      },
      (erro) => {
        console.error('Erro ao cadastrar livros:', erro);
      }
    );
  }

  resetarFormulario() {
    Object.keys(this.livroForm.controls).forEach(key => {
      this.livroForm.get(key)?.setValue(null, { onlySelf: true, emitEvent: false });
      this.livroForm.get(key)?.clearValidators();
      this.livroForm.get(key)?.updateValueAndValidity({ onlySelf: true, emitEvent: false });
    });
  }

  private obterId() {
    this.route.paramMap.subscribe(params => {
      if (params.get('id') != null) {
        const id = params.get('id');
        if (id !== null && id !== undefined) {
          this.id = parseInt(id, 10);
          console.log('ID do Livro: ', this.id);
          this.livroService.obterLivroCadastrado(this.id).subscribe((livro: Livros) => {
            console.log('Livro: ', livro);
              this.livroForm.patchValue(livro);
          });
        }
      }
    });
  }

  atualizarLivros(){
    if (this.livroForm.valid) {
      this.livroService.atualizarLivro(this.id, this.livroForm.value).subscribe((livro: Livros) => {
        if (livro != null) {
          this.mensagem = true;
          this.livroForm.patchValue(livro);
        }
      });
    }
  }

}
