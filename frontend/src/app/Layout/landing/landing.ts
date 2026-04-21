import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './landing.html',
  styleUrl: './landing.css'
})
export class Landing {

  members = [
  { id: 1, name: 'Yuvasri', photo: 'members/yuvasri.jpg' },
  { id: 2, name: 'Gokul', photo: 'members/gokul.jpg' },
  { id: 3, name: 'Darshini', photo: 'members/darshini.jpg' },
  { id: 4, name: 'Keerthesha', photo: 'members/keerthesha.jpg' }
];

  constructor(private router: Router) {}

  openLogin(id: number): void {
    this.router.navigate(['/login', id]);
  }

}