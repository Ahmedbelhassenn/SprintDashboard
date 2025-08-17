import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintsTableComponent } from './sprints-table.component';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('SprintsTableComponent', () => {
  let component: SprintsTableComponent;
  let fixture: ComponentFixture<SprintsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SprintsTableComponent, HttpClientModule, RouterTestingModule   ]
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
