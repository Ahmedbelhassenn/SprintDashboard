import { Component } from '@angular/core';
import { KpiService } from '../../../../services/kpi.service';
import { Chart, registerables } from 'chart.js';
import ChartDataLabels from 'chartjs-plugin-datalabels';



@Component({
  selector: 'app-sprint-rate',
  templateUrl: './sprint-rate.component.html',
  standalone: true ,
  imports : [],
  styleUrl: './sprint-rate.component.css'
})
export class SprintRateComponent {
  chart1: Chart | undefined;
  failureRate: number = 0;

  constructor(private kpiService: KpiService) {
    Chart.register(...registerables,ChartDataLabels);
  }

  ngOnInit() {
    this.fetchFailureRate();
  }

  fetchFailureRate() {
    this.kpiService.getFailureRate().subscribe(data => {
      this.failureRate = data[0]?.failureRate * 100; // Convertit en pourcentage
      this.createSprintChart();
    });
  }

  createSprintChart() {
    if (typeof document !== 'undefined') {
    const ctx = document.getElementById('sprintProgressChart') as HTMLCanvasElement;
    if (this.chart1) this.chart1.destroy(); // Détruit l'ancien graphique s'il existe

    this.chart1 = new Chart(ctx, {
      type: 'doughnut',
      data: {
        datasets: [{
          data: [100-this.failureRate, this.failureRate],
          backgroundColor: ['#6A5ACD', '#D3D3D3'], // Violet et gris
          borderWidth: 1
        }]
      },
      options: {
        rotation: -90,  // Rotation pour avoir un demi-cercle
        circumference: 180,
        cutout: '50%',  // Ajuste l'épaisseur
        plugins: {
          tooltip: { enabled: false },
          datalabels: {
            display: true,
            formatter: () =>'' ,
            color: '#000',
            anchor: 'center',
            font: { size: 16 }
          }
        }
      },
      plugins: [] // Intègre le plugin si nécessaire
    });
  }
  }
}
