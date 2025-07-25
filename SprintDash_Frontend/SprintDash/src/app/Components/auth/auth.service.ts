import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/member'; 

  constructor(private http: HttpClient) {}

  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }
  

  signup(data: { email: string; password: string; name: string }): Observable<any> {
    return this.http
      .post(`${this.apiUrl}/signup`, data)
      .pipe(
        catchError((error) => {
          console.error('Signup failed', error); // You can log or handle the error differently
          return throwError(() => new Error('Signup failed. Please try again later.'));
        })
      );
  }
}
