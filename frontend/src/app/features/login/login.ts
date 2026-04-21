import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent implements OnInit {

  selectedMember: any;

  members = [
  { id: 1, name: 'Yuvasri', photo: 'members/yuvasri.jpg' },
  { id: 2, name: 'Gokul', photo: 'members/gokul.jpg' },
  { id: 3, name: 'Darshini', photo: 'members/darshini.jpg' },
  { id: 4, name: 'Keerthesha', photo: 'members/keerthesha.jpg' }
];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.selectedMember = this.members.find(m => m.id === id);
  }

}