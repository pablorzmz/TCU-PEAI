import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaTematicaListarComponent } from './area-tematica-listar.component';

describe('AreaTematicaListarComponent', () => {
  let component: AreaTematicaListarComponent;
  let fixture: ComponentFixture<AreaTematicaListarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AreaTematicaListarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AreaTematicaListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
