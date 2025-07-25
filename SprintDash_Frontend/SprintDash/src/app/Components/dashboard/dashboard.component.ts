import { Component, OnInit } from '@angular/core';
import { KpiService } from '../../services/kpi.service';
import { VelocityComponent } from './Charts/velocity/velocity.component';
import { SprintRateComponent } from './Charts/sprint-rate/sprint-rate.component';
import { StoryPointsRateComponent } from './Charts/story-points-rate/story-points-rate.component';
import { RouterLink, RouterLinkActive } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone : true, 
  imports: [VelocityComponent, SprintRateComponent, StoryPointsRateComponent, RouterLink, RouterLinkActive],
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  totalSprints: number = 0;
  openedSprints: number = 0;
  totalTickets : number = 0;
  completedTickets : number = 0;
  completedStoryPoints: number = 0;
  totalStoryPoints: number = 0;
  totalBugs: number = 0;
  resolvedBugs: number = 0;
  isLoading: Boolean= true;
  someCondition: boolean = true; 

  constructor(private kpiService: KpiService) {
  }
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
          this.totalSprints = firstItem.totalSprints;
          this.openedSprints = firstItem.totalSprints-firstItem.totalSprintsCompleted;
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
          this.totalTickets = firstItem.totalTickets;
          this.completedTickets = firstItem.totalTicketsCompleted;
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
          this.totalStoryPoints = firstItem.totalStoryPoints;
          this.completedStoryPoints = firstItem.totalStoryPointsCompleted;
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
          this.totalBugs = firstItem.totalBugs;
          this.resolvedBugs = firstItem.bugsResolved;
        }
        this.isLoading = false;
      }
    )
  }
  
}  