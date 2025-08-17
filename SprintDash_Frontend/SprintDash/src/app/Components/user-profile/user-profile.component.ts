import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { MemberStats } from '../../models/kpi.models';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    standalone: true,
    imports: [RouterLink],
    styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {
    private apiUrl="http://localhost:8081/member/kpi/2";
    completedStoryPoints = 0;
    tasksToDo=0;
    tasksCompleted=0;
    isLoading= true;

    private http = inject(HttpClient);
    getScrumMemberKpi(): Observable<MemberStats>{
      return this.http.get<MemberStats>(`${this.apiUrl}`);
    }
    ngOnInit(): void {
        this.fetchMemberKpi();
    }
    fetchMemberKpi():void{
      this.getScrumMemberKpi().subscribe(
        (response) =>{
            this.completedStoryPoints=response.totalStoryPoints;
            this.tasksCompleted=response.tasksCompleted;
            this.tasksToDo=response.tasksToDo;
            this.isLoading = false;
        }
      )
    }
}
