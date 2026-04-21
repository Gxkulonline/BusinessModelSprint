import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Gokul } from './gokul';

describe('Gokul', () => {
  let component: Gokul;
  let fixture: ComponentFixture<Gokul>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Gokul],
    }).compileComponents();

    fixture = TestBed.createComponent(Gokul);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
