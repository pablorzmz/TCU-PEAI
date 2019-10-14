import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ForoListarComentariosComponent } from './foro-listar-comentarios.component';

describe('ForoListarComentariosComponent', () => {
  let component: ForoListarComentariosComponent;
  let fixture: ComponentFixture<ForoListarComentariosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ForoListarComentariosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ForoListarComentariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
