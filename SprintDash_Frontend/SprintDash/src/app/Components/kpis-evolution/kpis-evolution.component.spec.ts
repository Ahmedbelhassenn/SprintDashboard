import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KpisEvolutionComponent } from './kpis-evolution.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';

describe('KpisEvolutionComponent', () => {
  let component: KpisEvolutionComponent;
  let fixture: ComponentFixture<KpisEvolutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KpisEvolutionComponent, HttpClientModule, RouterTestingModule   ]
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
