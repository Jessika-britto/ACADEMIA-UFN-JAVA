import { Users } from './model/users';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Credentials } from './model/credentials';
import { Token } from './model/token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  login(credentials: Credentials): Observable<Token> {
    return this.http.post<Token>(`${this.baseUrl}/auth/login`, credentials);
  }

  registerUsers(users: Users): Observable<Token> {
    return this.http.post<Token>(`${this.baseUrl}/auth/register`, users);
  }

  listUsers(): Observable<Array<Users>> {
    return this.http.get<Array<Users>>(`${this.baseUrl}/auth/users`);
  }

  logout(): void {
    localStorage.removeItem('token');
  }

}
