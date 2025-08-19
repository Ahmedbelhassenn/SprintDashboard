import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Kpi, Sprint } from '../models/kpi.models';

@Injectable({
  providedIn: 'root'
})
export class KpiService {
  private baseUrl = 'http://localhost:8085';
  private http = inject(HttpClient);

  getSprints(): Observable<Sprint[]> {
    return this.http.get<Sprint[]>(`${this.baseUrl}/sprint/all`);
  }

  getTotalSprints(): Observable<Kpi[]> {
    return this.http.get<Kpi[]>(`${this.baseUrl}/totalSprints/all`);
  }

  getTotalStoryPoints(): Observable<Kpi[]> {
    return this.http.get<Kpi[]>(`${this.baseUrl}/totalStoryPoints/all`);
  }

  /*getTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.baseUrl}/ticket/all`);
  }*/

  getTotalTickets(): Observable<Kpi[]> {
    return this.http.get<Kpi[]>(`${this.baseUrl}/totalTickets/all`);
  }

  /*getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.baseUrl}/project/all`);
  }*/

 /* getMembers(): Observable<Member[]> {
    return this.http.get<Member[]>(`${this.baseUrl}/member/all`);
  }
*/
  getFailureRate(): Observable<Kpi[]> {
    return this.http.get<Kpi[]>(`${this.baseUrl}/sprint/failureRate/all`);
  }

  getTotalBugs(): Observable<Kpi[]> {
    return this.http.get<Kpi[]>(`${this.baseUrl}/total/bugs/all`);
  }

  getVelocityPerYear(): Observable<Kpi[]> {
    return this.http.get<Kpi[]>(`${this.baseUrl}/velocity/year/all`);
  }

  getVelocityPerTrimester(): Observable<Kpi[]> {
    return this.http.get<Kpi[]>(`${this.baseUrl}/velocity/trimester/all`);
  }

  getVelocityPerSemester(): Observable<Kpi[]> {
    return this.http.get<Kpi[]>(`${this.baseUrl}/velocity/semester/all`);
  }

  refrshJira(): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/jira`, '');
  }
  refreshKPIs(): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/kpis`,'');
  }
/*
  getTicketsBySprintId(sprintId: string): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.baseUrl}/ticket/sprint/${sprintId}`);
  }*/
}
