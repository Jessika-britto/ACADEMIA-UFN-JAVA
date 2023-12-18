import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogLivrosComponent } from './dialog-livros.component';

describe('DialogLivrosComponent', () => {
  let component: DialogLivrosComponent;
  let fixture: ComponentFixture<DialogLivrosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogLivrosComponent]
    });
    fixture = TestBed.createComponent(DialogLivrosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
