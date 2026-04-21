import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent implements OnInit {
  selectedMember: any;
  
  // 1. Add variables for data binding
  usernameInput: string = '';
  passwordInput: string = '';
  errorMessage: string = '';

  members = [
    { id: 1, name: 'Yuvasri', photo: 'members/yuvasri.jpg' },
    { id: 2, name: 'Gokul', photo: 'members/gokul.jpg' },
    { id: 3, name: 'Darshini', photo: 'members/darshini.jpg' },
    { id: 4, name: 'Keerthesha', photo: 'members/keerthesha.jpg' }
  ];

  // 2. Inject AuthService
  constructor(
    private route: ActivatedRoute, 
    private router: Router,
    private authService: AuthService 
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.selectedMember = this.members.find(m => m.id === id);
  }

  onLogin() {
    // 3. Normalize the name to match your AuthService keys (e.g., 'Yuvasri' -> 'yuvasri')
    const memberKey = this.selectedMember?.name.toLowerCase();

    // 4. Call the AuthService login method
    const isValid = this.authService.login(memberKey, this.usernameInput, this.passwordInput);

    if (isValid) {
      this.errorMessage = '';
      // Only navigate if login is true
      if (this.selectedMember?.name === 'Yuvasri') {
        this.router.navigate(['/yuvasri']);
      } else if (this.selectedMember?.name === 'Gokul') {
        this.router.navigate(['/gokul']);
      } else if (this.selectedMember?.name === 'Darshini') {
        this.router.navigate(['/darshini']);
      } else if (this.selectedMember?.name === 'Keerthesha') {
        this.router.navigate(['/keerthesha']);
      }
    } else {
      // 5. Show error message if invalid
      this.errorMessage = 'Invalid username or password for ' + this.selectedMember?.name;
    }
  }
}