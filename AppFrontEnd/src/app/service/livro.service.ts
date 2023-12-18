import { Livros } from './../model/livros';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LivroService {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  cadastrarLivros(livros: Livros): Observable<Livros> {
    return this.http.post<Livros>(`${this.baseUrl}/cadastrarLivro`, livros);
  }

  livrosCadastrados(): Observable<Array<Livros>> {
    return this.http.get<Array<Livros>>(`${this.baseUrl}/livros`);
  }

  obterLivroCadastrado(id: Number): Observable<Livros> {
    return this.http.get<Livros>(`${this.baseUrl}/livro/${id}`);
  }

  atualizarLivro(id: Number, livros: Livros): Observable<Livros> {
    return this.http.put<Livros>(`${this.baseUrl}/atualizarLivro/${id}`, livros);
  }

  removerLivro(id: Number): Observable<Livros> {
    return this.http.delete<Livros>(`${this.baseUrl}/removerLivro/${id}`);
  }
}
