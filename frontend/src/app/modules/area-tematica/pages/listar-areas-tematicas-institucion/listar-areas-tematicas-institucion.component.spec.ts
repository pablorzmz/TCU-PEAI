import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarAreasTematicasInstitucionComponent } from './listar-areas-tematicas-institucion.component';

describe('ListarAreasTematicasInstitucionComponent', () => {
  let component: ListarAreasTematicasInstitucionComponent;
  let fixture: ComponentFixture<ListarAreasTematicasInstitucionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarAreasTematicasInstitucionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarAreasTematicasInstitucionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
