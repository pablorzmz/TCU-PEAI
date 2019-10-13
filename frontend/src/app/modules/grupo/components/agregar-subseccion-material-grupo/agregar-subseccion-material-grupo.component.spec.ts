import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarSubseccionMaterialGrupoComponent } from './agregar-subseccion-material-grupo.component';

describe('AgregarSubseccionMaterialGrupoComponent', () => {
  let component: AgregarSubseccionMaterialGrupoComponent;
  let fixture: ComponentFixture<AgregarSubseccionMaterialGrupoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgregarSubseccionMaterialGrupoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarSubseccionMaterialGrupoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
