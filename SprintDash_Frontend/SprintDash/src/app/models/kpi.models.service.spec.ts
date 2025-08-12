import { TestBed } from '@angular/core/testing';

import { KpiModelsService } from './kpi.models.service';

describe('KpiModelsService', () => {
  let service: KpiModelsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KpiModelsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
