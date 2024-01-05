import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Token } from '../model/token';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.scss']
})
export class CadastrarComponent {

  declare usuarioForm: FormGroup;
  mensagem: boolean = false;

  constructor(private fb: FormBuilder,  private authService: AuthService, private router: Router) {
    this.inicializarFormulario();
  }

  inicializarFormulario() {
    this.usuarioForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  cadastraNovosUsuarios() {
    this.authService.registerUsers(this.usuarioForm.value).subscribe(
      (res: Token) => {
        if(res != null) {
            localStorage.setItem('token', res.token);
            this.mensagem = true;
            setTimeout(() => {
              this.router.navigate(['/login']);
            }, 2000);
        }
        this.resetarFormulario();
      },
      (erro) => {
        console.error('Erro ao cadastrar usuário:', erro);
      }
    );
  }

  resetarFormulario() {
    Object.keys(this.usuarioForm.controls).forEach(key => {
      this.usuarioForm.get(key)?.setValue(null, { onlySelf: true, emitEvent: false });
      this.usuarioForm.get(key)?.clearValidators();
      this.usuarioForm.get(key)?.updateValueAndValidity({ onlySelf: true, emitEvent: false });
    });
  }
}
