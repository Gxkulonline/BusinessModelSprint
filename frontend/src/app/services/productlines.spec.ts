import { TestBed } from '@angular/core/testing';

import { Productlines } from './productlines.service';

describe('Productlines', () => {
  let service: Productlines;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Productlines);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
