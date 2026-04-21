import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Keerthesha } from './keerthesha';

describe('Keerthesha', () => {
  let component: Keerthesha;
  let fixture: ComponentFixture<Keerthesha>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Keerthesha],
    }).compileComponents();

    fixture = TestBed.createComponent(Keerthesha);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
