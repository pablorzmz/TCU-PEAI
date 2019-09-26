import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarAreasTematicasComponent } from './listar-areas-tematicas.component';

describe('ListarAreasTematicasComponent', () => {
  let component: ListarAreasTematicasComponent;
  let fixture: ComponentFixture<ListarAreasTematicasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarAreasTematicasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarAreasTematicasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
