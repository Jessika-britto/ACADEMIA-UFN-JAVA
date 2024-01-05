
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Token } from '../model/token';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  declare loginForm: FormGroup;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router
    ) {
      this.inicializarFormulario();
  }

  ngOnInit(): void {
    this.authentication();
  }

  inicializarFormulario() {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  authentication() {
    this.authService.login(this.loginForm.value).subscribe(
      (res: Token) => {
        if(res != null) {
          localStorage.setItem('token', res.token);
          this.router.navigate(['/home']);
        }
      },
      (erro) => {
        console.error('Erro ao autenticar:', erro);
      }
    );
  }

}
