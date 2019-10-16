import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarSubseccionMaterialGrupoComponent } from './editar-subseccion-material-grupo.component';

describe('EditarSubseccionMaterialGrupoComponent', () => {
  let component: EditarSubseccionMaterialGrupoComponent;
  let fixture: ComponentFixture<EditarSubseccionMaterialGrupoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditarSubseccionMaterialGrupoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarSubseccionMaterialGrupoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
