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
    this.errorMessage = '';
    this.authService.login(this.usernameInput, this.passwordInput).subscribe(isValid => {
      if (isValid) {
        const user = this.authService.getUser();
        const roles = user.roles || [];

        // Navigate based on backend roles
        if (roles.includes('ROLE_ADMIN')) {
          this.router.navigate(['/yuvasri']); // Admins can go anywhere, default to Yuvasri
        } else if (roles.includes('ROLE_YUVASRI')) {
          this.router.navigate(['/yuvasri']);
        } else if (roles.includes('ROLE_GOKUL')) {
          this.router.navigate(['/gokul']);
        } else if (roles.includes('ROLE_DHARSHINI')) {
          this.router.navigate(['/darshini']);
        } else if (roles.includes('ROLE_KEERTHISHA')) {
          this.router.navigate(['/keerthesha']);
        } else {
          this.errorMessage = 'User has no assigned profile roles.';
        }
      } else {
        this.errorMessage = 'Invalid username or password for ' + this.selectedMember?.name;
      }
    });
  }
}