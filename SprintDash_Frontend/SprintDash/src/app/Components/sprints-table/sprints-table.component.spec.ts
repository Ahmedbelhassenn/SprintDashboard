import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintsTableComponent } from './sprints-table.component';

describe('SprintsTableComponent', () => {
  let component: SprintsTableComponent;
  let fixture: ComponentFixture<SprintsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SprintsTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SprintsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
