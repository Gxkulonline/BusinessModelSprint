import { TestBed } from '@angular/core/testing';

import { ProductLines } from './product-lines';

describe('ProductLines', () => {
  let service: ProductLines;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductLines);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
