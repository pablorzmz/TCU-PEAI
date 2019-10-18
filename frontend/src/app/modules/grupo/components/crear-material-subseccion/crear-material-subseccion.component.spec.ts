import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearMaterialSubseccionComponent } from './crear-material-subseccion.component';

describe('CrearMaterialSubseccionComponent', () => {
  let component: CrearMaterialSubseccionComponent;
  let fixture: ComponentFixture<CrearMaterialSubseccionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrearMaterialSubseccionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrearMaterialSubseccionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
