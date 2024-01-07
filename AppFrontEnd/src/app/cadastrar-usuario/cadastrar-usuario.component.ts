import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Users } from '../model/users';

@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.scss']
})
export class CadastrarUsuarioComponent implements OnInit {
  declare id: number;
  declare usuarioForm: FormGroup;
  mensagem: boolean = false;
  mensagemAtualizacao: boolean = false;

  constructor(private fb: FormBuilder, private authService: AuthService,private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.inicializarFormulario();
    this.obterId();
  }

  inicializarFormulario() {
    this.usuarioForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      role: ['', Validators.required]
    });
  }

  cadastraNovosUsuarios() {
    this.authService.cadastrarUsuario(this.usuarioForm.value).subscribe(
      (users: Users) => {
        if(users != null) {
            this.mensagem = true;
        }
        this.resetarFormulario();
      },
      (erro) => {
        console.error('Erro ao cadastrar usuarios:', erro);
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

  private obterId() {
    this.route.paramMap.subscribe(params => {
      if (params.get('id') != null) {
        const id = params.get('id');
        if (id !== null && id !== undefined) {
          this.id = parseInt(id, 10);
          this.authService.obterUsuario(this.id).subscribe((user: Users) => {
              this.usuarioForm.patchValue(user);
          });
        }
      }
    });
  }

  atualizarUsuarios(){
    if (this.usuarioForm.valid) {
      this.authService.atualizarUsuario(this.id, this.usuarioForm.value).subscribe((user: Users) => {
        if (user != null) {
          this.mensagem = true;
          this.usuarioForm.patchValue(user);
          this.router.navigate(['/usuarios']);
        }
      });
    }
  }

}
