import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintRateComponent } from './sprint-rate.component';
import { HttpClientModule } from '@angular/common/http';

describe('SprintRateComponent', () => {
  let component: SprintRateComponent;
  let fixture: ComponentFixture<SprintRateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SprintRateComponent, HttpClientModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SprintRateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
