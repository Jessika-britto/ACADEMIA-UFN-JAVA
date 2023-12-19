import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Usuarios } from '../model/usuarios';
import { UsuariosService } from '../service/usuarios.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.scss']
})
export class CadastrarComponent {

  declare usuarioForm: FormGroup;
  mensagem: boolean = false;

  constructor(private fb: FormBuilder, private usuariosService: UsuariosService, private router: Router) {
    this.inicializarFormulario();
  }

  inicializarFormulario() {
    this.usuarioForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', Validators.required],
    });
  }

  cadastraNovosUsuarios() {
    this.usuariosService.cadastrarUsuarios(this.usuarioForm.value).subscribe(
      (usuarioAdicionado: Usuarios) => {
        if(usuarioAdicionado != null) {
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
