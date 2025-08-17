import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoryPointsRateComponent } from './story-points-rate.component';
import { HttpClientModule } from '@angular/common/http';

describe('StoryPointsRateComponent', () => {
  let component: StoryPointsRateComponent;
  let fixture: ComponentFixture<StoryPointsRateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StoryPointsRateComponent, HttpClientModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StoryPointsRateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
