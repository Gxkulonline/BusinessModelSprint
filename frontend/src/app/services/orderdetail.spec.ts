import { TestBed } from '@angular/core/testing';

import { Orderdetail } from './orderdetail.service';

describe('Orderdetail', () => {
  let service: Orderdetail;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Orderdetail);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
