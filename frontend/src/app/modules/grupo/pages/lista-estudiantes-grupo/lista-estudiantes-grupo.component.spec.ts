import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaEstudiantesGrupoComponent } from './lista-estudiantes-grupo.component';

describe('AgregarEstudiantesGrupoComponent', () => {
  let component: ListaEstudiantesGrupoComponent;
  let fixture: ComponentFixture<ListaEstudiantesGrupoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaEstudiantesGrupoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaEstudiantesGrupoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
