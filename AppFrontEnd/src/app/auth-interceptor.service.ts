import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // Obtém o token do Local Storage
    const token = localStorage.getItem('token');

    // Clona a requisição e adiciona o token ao cabeçalho de autorização
    const clonedRequest = request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });

    // Envia a requisição clonada
    return next.handle(clonedRequest);
  }
}
