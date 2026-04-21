import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductLines } from './product-lines';

describe('ProductLines', () => {
  let component: ProductLines;
  let fixture: ComponentFixture<ProductLines>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductLines],
    }).compileComponents();

    fixture = TestBed.createComponent(ProductLines);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
