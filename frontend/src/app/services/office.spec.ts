import { TestBed } from '@angular/core/testing';

import { Office } from './office';

describe('Office', () => {
  let service: Office;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Office);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
