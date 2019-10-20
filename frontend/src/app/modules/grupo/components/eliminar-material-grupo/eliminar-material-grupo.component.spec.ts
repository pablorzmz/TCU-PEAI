import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarMaterialGrupoComponent } from './eliminar-material-grupo.component';

describe('EliminarMaterialGrupoComponent', () => {
  let component: EliminarMaterialGrupoComponent;
  let fixture: ComponentFixture<EliminarMaterialGrupoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EliminarMaterialGrupoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EliminarMaterialGrupoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
