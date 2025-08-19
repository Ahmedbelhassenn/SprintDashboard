import { Component, inject, OnInit } from '@angular/core';
import { KpiService } from '../../services/kpi.service';
import { VelocityComponent } from './Charts/velocity/velocity.component';
import { SprintRateComponent } from './Charts/sprint-rate/sprint-rate.component';
import { StoryPointsRateComponent } from './Charts/story-points-rate/story-points-rate.component';
import { RouterLink, RouterLinkActive } from '@angular/router';


@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    standalone: true,
    imports: [VelocityComponent, SprintRateComponent, StoryPointsRateComponent, RouterLink, RouterLinkActive],
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  totalSprints = 0;
  openedSprints = 0;
  totalTickets  = 0;
  completedTickets  = 0;
  completedStoryPoints = 0;
  totalStoryPoints = 0;
  totalBugs = 0;
  resolvedBugs = 0;
  isLoading= true;
  someCondition = true; 


  private kpiService = inject(KpiService);
  ngOnInit(): void {
    this.fetchTotalSprints(); 
    this.fetchTotalTickets();
    this.fetchTotalStoryPoints();
    this.fetchBugs();
  
  }

  fetchTotalSprints(): void {
    this.kpiService.getTotalSprints().subscribe(
      (response) => {
        if (response.length > 0) {
          const firstItem = response[0];
          const totalSprints = firstItem.totalSprints || 0;
          const totalSprintsCompleted = firstItem.totalSprintsCompleted  || 0
          this.totalSprints = firstItem.totalSprints || 0;
          this.openedSprints = totalSprints - totalSprintsCompleted;
        }
        this.isLoading = false;
      },
    );
  }
  fetchTotalTickets(): void {
    this.kpiService.getTotalTickets().subscribe(
      (response) => {
        if (response.length > 0) {
          const firstItem = response[0];
          this.totalTickets = firstItem.totalTickets || 0;
          this.completedTickets = firstItem.totalTicketsCompleted  || 0;
        }
        this.isLoading = false;
      }
    )
  }
  fetchTotalStoryPoints(): void {
    this.kpiService.getTotalStoryPoints().subscribe(
      (response) => {
        if (response.length > 0) {
          const firstItem = response[0];
          this.totalStoryPoints = firstItem.totalStoryPoints || 0;
          this.completedStoryPoints = firstItem.totalStoryPointsCompleted || 0;
        }
        this.isLoading = false;
      }
    )
  }
  fetchBugs(): void {
    this.kpiService.getTotalBugs().subscribe(
      (response) => {
        if (response.length > 0) {
          const firstItem = response[0];
          this.totalBugs = firstItem.totalBugs || 0;
          this.resolvedBugs = firstItem.bugsResolved || 0;
        }
        this.isLoading = false;
      }
    )
  }

  refreshKPIs() {
    this.kpiService.refrshJira().subscribe({
      next: (response) => {
        console.log('Jira refreshed successfully:', response);
      },
      error: (error) => { 
        console.error('Failed to refresh Jira:', error);
      }
    });
  }
  
}  