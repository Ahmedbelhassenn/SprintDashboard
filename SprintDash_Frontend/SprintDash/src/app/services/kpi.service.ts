import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KpiService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getSprints(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/sprint/all`);
  }
  getTotalSprints(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/totalSprints/all`);
  }
  getTotalStoryPoints(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/totalStoryPoints/all`);
  }
  getTickets(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/ticket/all`);
  }
  getTotalTickets(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/totalTickets/all`);
  }
  getProjects(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/project/all`);
  }
  getMembers(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/member/all`);
  }
  getFailureRate(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/sprint/failureRate/all`);
  }
  getTotalBugs(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/total/bugs/all`);
  }
  getVelocityPerYear(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/velocity/year/all`);
  }
  getVelocityPerTrimester(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/velocity/trimester/all`);
  }
  getVelocityPerSemester(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/velocity/semester/all`);
  }
  getTicketsBySprintId(sprintId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/ticket/sprint/${sprintId}`);
  }
}
