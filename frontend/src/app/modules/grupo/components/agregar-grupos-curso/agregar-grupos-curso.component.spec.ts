import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarGruposCursoComponent } from './agregar-grupos-curso.component';

describe('AgregarGruposCursoComponent', () => {
  let component: AgregarGruposCursoComponent;
  let fixture: ComponentFixture<AgregarGruposCursoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgregarGruposCursoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarGruposCursoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
