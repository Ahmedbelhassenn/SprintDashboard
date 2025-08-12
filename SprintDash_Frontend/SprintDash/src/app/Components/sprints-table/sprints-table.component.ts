import { Component, inject, OnInit } from '@angular/core';
import { KpiService } from '../../services/kpi.service';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { DatePipe } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Sprint } from '../../models/kpi.models.service';


@Component({
    selector: 'app-sprints-table',
    templateUrl: './sprints-table.component.html',
    imports: [FormsModule, MatTableModule, DatePipe, RouterLink, RouterLinkActive],
    styleUrl: './sprints-table.component.css'
})
export class SprintsTableComponent implements OnInit {
  sprints: Sprint[] = [];
  sprintData: Sprint[] = [];
  displayedColumns: string[] = ['name', 'startDate', 'endDate', 'state'];


  private kpiService = inject(KpiService);
  ngOnInit(): void {
    this.kpiService.getSprints().subscribe(
      (data) => {
      this.sprints = data;
      this.makeDataOrdered();
    });
   
    
  }
  makeDataOrdered(): void {
    const sortedNames = this.sprints.map(v => v.name.replace(/^Tableau\s/, ''))
      .sort((b, a) => a.localeCompare(b, undefined, { numeric: true })); // Trier de manière alphanumérique
    this.sprintData=sortedNames.map(name =>
      this.sprints.find(v => v.name.replace(/^Tableau\s/, '') === name)
    ).filter((v): v is Sprint => v !== undefined);
    this.sprintData.forEach(v =>
      v.name=v.name.replace(/^Tableau\s/, '')
    )
  }
}
