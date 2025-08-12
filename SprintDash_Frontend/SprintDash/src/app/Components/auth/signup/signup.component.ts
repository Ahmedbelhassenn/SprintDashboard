import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { FormsModule, NgForm } from '@angular/forms';
import { NgIf } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { Member } from '../../../models/kpi.models.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    NgIf,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  member: Member = {
    id : 1,
    name: '',
    email: '',
    password: '',
    phoneNumber: '',
    gender: '',
    birthDate: ''
  };

  private authService = inject(AuthService);
  private router = inject(Router);
  onSignup(signupForm: NgForm ) {
    if (signupForm.invalid) {
      Object.keys(signupForm.controls).forEach((field) => {
        const control = signupForm.controls[field];
        control.markAsTouched({ onlySelf: true });
      });
      alert('Veuillez remplir tous les champs requis correctement avant de continuer.');
      return;
    }

    this.authService.signup(this.member).subscribe({
      next: (response) => {
        console.log('Inscription réussie:', response);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error(error.error);
        if (error.status === 409 && error.error.error === 'Conflict') {
          alert('Ce mail est déjà utilisé. Veuillez en choisir un autre.');
        } else if (error.status === 400 && error.error.error === 'Bad Request') {
          alert('Essayer un autre mot de passe plus fort.');
        } else {
          alert('Erreur d\'inscription: ' + error.message);
        }
      }
    });
  }
}

