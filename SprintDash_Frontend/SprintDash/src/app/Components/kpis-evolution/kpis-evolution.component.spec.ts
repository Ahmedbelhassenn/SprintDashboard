import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KpisEvolutionComponent } from './kpis-evolution.component';

describe('KpisEvolutionComponent', () => {
  let component: KpisEvolutionComponent;
  let fixture: ComponentFixture<KpisEvolutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [KpisEvolutionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KpisEvolutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
