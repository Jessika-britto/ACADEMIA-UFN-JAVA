export class Livros {
  titulo: string;
  autor: string;
  genero: string;
  editora: string;
  classificacao: string;

  constructor(titulo: string, autor: string, genero: string, editora: string, classificacao: string) {
    this.titulo = titulo;
    this.autor = autor;
    this.genero = genero;
    this.editora = editora;
    this.classificacao = classificacao;
  }
}
