
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuarios } from '../model/usuarios';


@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  cadastrarUsuarios(usuarios: Usuarios): Observable<Usuarios> {
    return this.http.post<Usuarios>(`${this.baseUrl}/cadastrar`, usuarios);
  }

  usuariosCadastrados(): Observable<Array<Usuarios>> {
    return this.http.get<Array<Usuarios>>(`${this.baseUrl}/usuarios`);
  }
}
