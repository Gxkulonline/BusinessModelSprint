import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Darshini } from './darshini';

describe('Darshini', () => {
  let component: Darshini;
  let fixture: ComponentFixture<Darshini>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Darshini],
    }).compileComponents();

    fixture = TestBed.createComponent(Darshini);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
