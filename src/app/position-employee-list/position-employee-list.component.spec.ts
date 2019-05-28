import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PositionEmployeeListComponent } from './position-employee-list.component';

describe('PositionEmployeeListComponent', () => {
  let component: PositionEmployeeListComponent;
  let fixture: ComponentFixture<PositionEmployeeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PositionEmployeeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PositionEmployeeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
