import { Component, inject, OnInit } from '@angular/core';
import { KpiService } from '../../../services/kpi.service';
import {  MatFormFieldModule } from "@angular/material/form-field";
import { MatOption, MatSelect } from '@angular/material/select';
import { NgFor } from '@angular/common';
import { Sprint } from '../../../models/kpi.models';

@Component({
    selector: 'app-sprint-list',
    templateUrl: './sprint-list.component.html',
    styleUrls: ['./sprint-list.component.css'],
    standalone: true,
    imports: [MatFormFieldModule, MatSelect, MatOption, NgFor]
})
export class SprintListComponent implements OnInit {
  sprints: Sprint[] = [];
  filteredSprints: Sprint[] = [];
  years: string[] = [];
  states: string[] = ['All', 'Active', 'Closed'];
  selectedYear = 'All';
  selectedState = 'All';

  private kpiService = inject(KpiService);  

  ngOnInit(): void {
    this.kpiService.getSprints().subscribe((data: Sprint[]) => {
      this.sprints = data;

      // Extract and sort years in descending order
      this.years = Array.from(new Set(this.sprints.map(s =>
        new Date(s.startDate ?? '').getFullYear().toString()
      ))).sort((a, b) => parseInt(b) - parseInt(a));
      this.years.unshift('All'); // Add "All" option for showing all sprints

      // Set default selected year and state, then filter sprints
      this.selectedYear = this.years[0];
      this.selectedState = 'All';
      this.filterSprints();
    });
  }

  filterSprints(): void {
    this.filteredSprints = this.sprints
      .filter(sprint => (this.selectedYear === 'All' || new Date(sprint.startDate ?? '').getFullYear().toString() === this.selectedYear))
      .filter(sprint => (this.selectedState === 'All' || sprint.state?.toLowerCase() === this.selectedState.toLowerCase()));
  }

  onYearSelectionChange(): void {
    this.filterSprints();
  }

  onStateSelectionChange(): void {
    this.filterSprints();
  }
}
