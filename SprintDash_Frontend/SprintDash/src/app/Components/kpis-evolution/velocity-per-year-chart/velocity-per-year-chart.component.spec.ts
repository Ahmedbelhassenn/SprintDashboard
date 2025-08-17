import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VelocityPerYearChartComponent } from './velocity-per-year-chart.component';
import { HttpClientModule } from '@angular/common/http';

describe('VelocityPerYearChartComponent', () => {
  let component: VelocityPerYearChartComponent;
  let fixture: ComponentFixture<VelocityPerYearChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VelocityPerYearChartComponent, HttpClientModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VelocityPerYearChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
