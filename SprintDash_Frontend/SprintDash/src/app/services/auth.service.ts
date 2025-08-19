import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Member } from '../models/kpi.models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8085/member';

  private http = inject(HttpClient);
  signup(member: Member): Observable<Member> {
    return this.http.post<Member>(`${this.baseUrl}/signup`, member);
  }
}
