import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HiringInfoListComponent } from './hiring-info-list.component';

describe('HiringInfoListComponent', () => {
  let component: HiringInfoListComponent;
  let fixture: ComponentFixture<HiringInfoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HiringInfoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HiringInfoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
