import { TestBed } from '@angular/core/testing';

import { OrderDetail } from './order-detail';

describe('OrderDetail', () => {
  let service: OrderDetail;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderDetail);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
