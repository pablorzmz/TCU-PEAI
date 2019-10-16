import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarSubseccionMaterialGrupoComponent } from './eliminar-subseccion-material-grupo.component';

describe('EliminarSubseccionMaterialGrupoComponent', () => {
  let component: EliminarSubseccionMaterialGrupoComponent;
  let fixture: ComponentFixture<EliminarSubseccionMaterialGrupoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EliminarSubseccionMaterialGrupoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EliminarSubseccionMaterialGrupoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
