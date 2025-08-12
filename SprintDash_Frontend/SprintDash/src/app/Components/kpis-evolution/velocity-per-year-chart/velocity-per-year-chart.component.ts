import { Component, OnInit, AfterViewInit, ViewChild, ElementRef, PLATFORM_ID, inject } from '@angular/core';

import { isPlatformBrowser } from '@angular/common';
import { KpiService } from '../../../services/kpi.service';
import { Chart, registerables } from 'chart.js';
import ChartDataLabels from 'chartjs-plugin-datalabels';
import {  MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { Kpi } from '../../../models/kpi.models.service';




@Component({
    selector: 'app-velocity-per-year-chart',
    templateUrl: './velocity-per-year-chart.component.html',
    imports: [MatTableModule, MatTabsModule],
    styleUrls: ['./velocity-per-year-chart.component.css']
})
export class VelocityPerYearChartComponent implements OnInit, AfterViewInit {
  
  velocities: Kpi[] = [];
  displayedColumns: string[] = ['year', 'velocity']; // Table column headers
  @ViewChild('velocityChartPerYear', { static: false }) velocityChartPerYear!: ElementRef;
  private chart5: Chart | undefined;
  
  constructor() {
    Chart.register(...registerables, ChartDataLabels);
    
  }
  private platformId = inject(PLATFORM_ID);
  private kpiService = inject(KpiService);
  ngOnInit(): void {
    // Static data for testing
    const staticData: Kpi[] = [
      { year: '2020', velocity: 120 },
      { year: '2021', velocity: 140 },
      { year: '2022', velocity: 110 },
      { year: '2023', velocity: 130 },
    ];

    this.kpiService.getVelocityPerYear().subscribe((data: Kpi[]) => {
      // Merge static and dynamic data, then sort
      const dynamicData = data || []; // Handle potential undefined values
      this.velocities = [...staticData, ...dynamicData].sort(this.sortYears);

      // Create chart after data is ready
      this.createChart();
    });
  }

  ngAfterViewInit(): void {
    // Ensure chart creation only in the browser environment
    if (isPlatformBrowser(this.platformId)) {
      this.createChart();
    }
  }

  // Sort years numerically
  sortYears(a: Kpi, b: Kpi): number {
    return parseInt(a.year ?? '0', 10) - parseInt(b.year ?? '0', 10);
  }

  createChart(): void {
    // Check if data and chart element are available
    if (this.velocityChartPerYear && this.velocities.length > 0) {
      const years = this.velocities.map(v => `Year ${v.year}`);
      const velocityValues = this.velocities
        .map(v => typeof v.velocity === 'number' ? v.velocity : undefined)
        .filter((v): v is number => v !== undefined);

      const ctx = this.velocityChartPerYear.nativeElement.getContext('2d');

      if (ctx) {
        // Destroy the previous chart if it exists
        if (this.chart5) {
          this.chart5.destroy();
        }

        // Create a new chart
        this.chart5 = new Chart(ctx, {
          type: 'line',
          data: {
            labels: years,
            datasets: [
              {
                label: 'Velocity',
                data: velocityValues,
                fill: true,
                borderColor: 'rgba(75, 192, 192, 1)',
                
               
              }
            ]
          },
          options: {
            plugins: {
              datalabels: {
                display: false,
              },
              tooltip: {
                callbacks: {
                  label: (context) => `Velocity: ${context.raw}`,
                },
                bodyFont: {
                  size: 14
                },
                titleFont: {
                  size: 16
                }
              }
            },
            scales: {
              y: {
                beginAtZero: true,
              },
            },
          }
        });
      }
    }
  }
}
