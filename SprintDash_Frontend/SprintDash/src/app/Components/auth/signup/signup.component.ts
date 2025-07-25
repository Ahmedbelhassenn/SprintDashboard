import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatOption } from '@angular/material/core';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatOption, NgIf, RouterLink, MatInputModule, MatButtonModule],
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  member = {
    name: '',
    email: '',
    password: '',
    phoneNumber: '',
    gender: '',
    birthDate: ''
  };

  constructor(private authService: AuthService, private router:Router) {}
 
  onSignup(signupForm: any) {
    // Vérifiez si le formulaire est invalide
    if (signupForm.invalid) {
      // Marquez tous les champs comme touchés pour forcer l'affichage des erreurs
      Object.keys(signupForm.controls).forEach((field) => {
        const control = signupForm.controls[field];
        control.markAsTouched({ onlySelf: true });
      });
      alert('Veuillez remplir tous les champs requis correctement avant de continuer.');
      return;
    }
  
    // Si le formulaire est valide, continuez avec l'inscription
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
          alert('Erreur d\'inscription: ' + error.message); // Message général en cas d'erreur inattendue
        }
      }
    }
      
    );
  }
  
}
