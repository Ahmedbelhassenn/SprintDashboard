import {
  Component,
  OnInit,
  AfterViewInit,
  ViewChild,
  ElementRef,
  PLATFORM_ID,
  inject
} from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Chart, registerables } from 'chart.js';
import ChartDataLabels from 'chartjs-plugin-datalabels';
import { KpiService } from '../../../services/kpi.service';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { Kpi } from '../../../models/kpi.models.service';

@Component({
    selector: 'app-velocity-per-semester-chart',
    templateUrl: './velocity-per-semester-chart.component.html',
    styleUrls: ['./velocity-per-semester-chart.component.css'],
    imports: [CommonModule, MatTableModule, MatTabsModule]
})
export class VelocityPerSemesterChartComponent
  implements OnInit, AfterViewInit
{
  velocities: Kpi[] = [];
  chart2?: Chart;
  displayedColumns: string[] = ['semester', 'velocity'];

  @ViewChild('velocityChartPerSemester', { static: false })
  velocityChartPerSemester!: ElementRef;

  
  private kpiService = inject(KpiService);
  private platformId = inject(PLATFORM_ID);

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      Chart.register(...registerables, ChartDataLabels);
    }

    // Données statiques pour test
    const staticData: Kpi[] = [
      { semester: '2023-H1', velocity: 120 },
      { semester: '2023-H2', velocity: 130 },
      { semester: '2024-H1', velocity: 110 },
      { semester: '2022-H2', velocity: 100 }
    ];

    // Récupération des données dynamiques
    this.kpiService.getVelocityPerSemester().subscribe((data: Kpi[]) => {
      const dynamicData = data?.sort(this.sortSemesters) || [];
      this.velocities = [...staticData, ...dynamicData].sort(this.sortSemesters);
      this.createChart(); // Créer le chart après avoir reçu les données
    });
  }

  ngAfterViewInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.createChart();
    }
  }

  sortSemesters(a: Kpi, b: Kpi): number {
    const [yearA, halfA] = (a.semester ?? '').split('-');
    const [yearB, halfB] = (b.semester ?? '').split('-');

    if (yearA === yearB) {
      return halfA.localeCompare(halfB);
    }
    return yearA.localeCompare(yearB);
  }

  createChart(): void {
    if (!isPlatformBrowser(this.platformId)) return;

    const semesters = this.velocities.map((v) => v.semester);
    const velocityValues = this.velocities.map((v) => v.velocity).filter((v): v is number => v !== undefined && v !== null);

    if (this.chart2) {
      this.chart2.destroy();
    }

    const ctx = this.velocityChartPerSemester.nativeElement.getContext('2d');
    if (ctx) {
      this.chart2 = new Chart(ctx, {
        type: 'line',
        data: {
          labels: semesters,
          datasets: [
            {
              label: 'Velocity',
              data: velocityValues,
              fill: true,
              borderColor: 'rgba(75, 192, 192, 1)',
              backgroundColor: 'rgba(75, 192, 192, 0.2)',
              tension: 0.1
            }
          ]
        },
        options: {
          plugins: {
            datalabels: {
              display: false
            },
            tooltip: {
              callbacks: {
                label: (context) => `Velocity: ${context.raw}`
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
              title: {
                display: true
              }
            }
          }
        }
      });
    }
  }
}
