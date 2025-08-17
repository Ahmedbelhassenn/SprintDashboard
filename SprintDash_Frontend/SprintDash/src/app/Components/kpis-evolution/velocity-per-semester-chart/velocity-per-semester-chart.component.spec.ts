import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VelocityPerSemesterChartComponent } from './velocity-per-semester-chart.component';
import { HttpClientModule } from '@angular/common/http';

describe('VelocityPerSemesterChartComponent', () => {
  let component: VelocityPerSemesterChartComponent;
  let fixture: ComponentFixture<VelocityPerSemesterChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VelocityPerSemesterChartComponent, HttpClientModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VelocityPerSemesterChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
