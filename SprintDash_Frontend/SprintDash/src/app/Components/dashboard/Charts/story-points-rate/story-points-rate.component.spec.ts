import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoryPointsRateComponent } from './story-points-rate.component';

describe('StoryPointsRateComponent', () => {
  let component: StoryPointsRateComponent;
  let fixture: ComponentFixture<StoryPointsRateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StoryPointsRateComponent]
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
