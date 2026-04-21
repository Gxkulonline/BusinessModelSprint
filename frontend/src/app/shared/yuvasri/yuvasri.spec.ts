import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Yuvasri } from './yuvasri';

describe('Yuvasri', () => {
  let component: Yuvasri;
  let fixture: ComponentFixture<Yuvasri>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Yuvasri],
    }).compileComponents();

    fixture = TestBed.createComponent(Yuvasri);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
