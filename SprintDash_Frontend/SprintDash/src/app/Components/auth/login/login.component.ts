import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router'; // Import Router
import { MatError, MatFormField, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';

import { NgIf } from '@angular/common';
@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    imports: [MatFormField, MatLabel, MatError, FormsModule, MatCheckboxModule, NgIf, MatInputModule, MatButtonModule, RouterLink],
    styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials = { email: '', password: '' };


  private http= inject(HttpClient);
  private router = inject(Router);

  onLogin() {
    console.log(this.credentials); 
    this.http.post('http://localhost:8081/member/login', this.credentials).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Login failed:', error);
        alert('Login failed: ' + (error.error?.message || error.message));
      }
    });
  }
}
