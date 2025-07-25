import { Component, OnInit , Inject} from '@angular/core';
import { KpiService } from '../../../../services/kpi.service';
import { Chart, registerables } from 'chart.js';
import ChartDataLabels from 'chartjs-plugin-datalabels';



@Component({
  selector: 'app-story-points-rate',
  templateUrl: './story-points-rate.component.html',
  standalone: true ,
  imports : [],
  styleUrl: './story-points-rate.component.css'
})
export class StoryPointsRateComponent implements OnInit {
  chart4: Chart | undefined;
  rate : string ='';
  completedStoryPoints: number = 0;
  totalStoryPoints: number = 0;
  constructor(private kpiService: KpiService) {
    Chart.register(...registerables,ChartDataLabels);
  }

  ngOnInit() {
    this.fetchTotalStoryPoints();
  }

  fetchTotalStoryPoints(): void {
    this.kpiService.getTotalStoryPoints().subscribe(
      (response) => {
        if (response.length > 0) {
          const firstItem = response[0];
          this.totalStoryPoints = firstItem.totalStoryPoints;
          this.completedStoryPoints = firstItem.totalStoryPointsCompleted;
          this.rate=((this.completedStoryPoints/(this.totalStoryPoints))*100).toFixed(2);
        }
        this.createStoryChart();
      }
    )
  }

  createStoryChart() {
    if (typeof document !== 'undefined') {
          const ctx = document.getElementById('storyPointsChart') as HTMLCanvasElement;
    if (this.chart4) this.chart4.destroy(); // Détruit l'ancien graphique s'il existe
    this.chart4 = new Chart(ctx, {
      type: 'doughnut',
      data: {
        datasets: [{
          data: [parseFloat(this.rate) ,100-parseFloat(this.rate)],
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
