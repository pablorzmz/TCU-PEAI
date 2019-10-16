import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerMaterialComponent } from './ver-material.component';

describe('VerMaterialComponent', () => {
  let component: VerMaterialComponent;
  let fixture: ComponentFixture<VerMaterialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerMaterialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
