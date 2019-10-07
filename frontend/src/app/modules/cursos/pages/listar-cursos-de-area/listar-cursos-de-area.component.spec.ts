import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarCursosDeAreaComponent } from './listar-cursos-de-area.component';

describe('ListarCursosDeAreaComponent', () => {
  let component: ListarCursosDeAreaComponent;
  let fixture: ComponentFixture<ListarCursosDeAreaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarCursosDeAreaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarCursosDeAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
