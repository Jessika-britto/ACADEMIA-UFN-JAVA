import { UserLogged } from './../../model/user-logged';
import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { AuthService } from 'src/app/auth.service';
import { Router } from '@angular/router';

enum Permissao {
  Usuario = 'USER',
  Admin = 'ADMIN'
}

interface sidebarMenu {
  link: string;
  icon: string;
  menu: string;
  permissoes: Permissao[];
  visible: boolean;
}

@Component({
  selector: 'app-full',
  templateUrl: './full.component.html',
  styleUrls: ['./full.component.scss']
})
export class FullComponent {

  search: boolean = false;
  user: UserLogged = new UserLogged();

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(
    private breakpointObserver: BreakpointObserver,
    private authService: AuthService,
    private router: Router
    ) { }

    ngOnInit(): void {
      this.loggedUser();
    }

  routerActive: string = "activelink";

  sidebarMenu: sidebarMenu[] = [
    {
      link: "/cadastrarLivro",
      icon: "bookmark",
      menu: "Cadastrar",
      permissoes: [Permissao.Usuario, Permissao.Admin],
      visible: false
    },
    {
      link: "/livros",
      icon: "book",
      menu: "Livros",
      permissoes: [Permissao.Usuario, Permissao.Admin],
      visible: false
    },
    {
      link: "/usuarios",
      icon: "user",
      menu: "Usuarios",
      permissoes: [Permissao.Admin],
      visible: false
    }
  ]

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  loggedUser(): void {
    this.authService.getLoggedUser().subscribe((user: UserLogged) => {
        this.user = user;
        this.checkPermissions();
    },
    (erro) => {
      console.error('Erro ao retorna usuário logado:', erro);
    });
  }

  checkPermissions(): void {
    const isAdmin = this.hasAtLeastOneAdminPermission();

    this.sidebarMenu.forEach(menu => {
      if (menu.link === '/usuarios') {
        menu.visible = isAdmin;
      } else {
        menu.visible = this.hasRequiredPermissions(menu.permissoes, isAdmin);
      }
    });
  }

  hasRequiredPermissions(requiredPermissions: Permissao[], isAdmin: boolean): boolean {
    if (isAdmin) {
      return true;
    }

    return this.user?.permissions?.some(p => requiredPermissions.includes(p as Permissao)) || false;
  }

  hasAtLeastOneAdminPermission(): boolean {
    return this.user?.permissions?.includes(Permissao.Admin) || false;
  }

  editarPerfil() {
    this.router.navigate(['/atualizarUsuario', this.user.id]);
  }

  get isAdmin() {
    return this.user?.permissions?.includes(Permissao.Admin);
  }
}
