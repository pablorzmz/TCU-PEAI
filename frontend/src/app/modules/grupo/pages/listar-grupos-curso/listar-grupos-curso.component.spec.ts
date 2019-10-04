import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarGruposCursoComponent } from './listar-grupos-curso.component';

describe('ListarGruposCursoComponent', () => {
  let component: ListarGruposCursoComponent;
  let fixture: ComponentFixture<ListarGruposCursoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarGruposCursoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarGruposCursoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
