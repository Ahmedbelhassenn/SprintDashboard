import { Component, OnInit } from '@angular/core';
import { KpiService } from '../../../../services/kpi.service';
import { Chart, registerables } from 'chart.js';
import { NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-velocity',
  templateUrl: './velocity.component.html',
  standalone: true ,
  imports : [NgFor, FormsModule],
  styleUrls: ['./velocity.component.css']
})
export class VelocityComponent implements OnInit {
  velocities: any[] = [];
  sprintData: any[] = [];
  filteredVelocities: any[] = []; // Données filtrées
  years: string[] = []; // Liste des années disponibles
  selectedYear = 'All'; // Année sélectionnée par défaut
  chart3: Chart | undefined;

  constructor(private kpiService: KpiService) {
    Chart.register(...registerables);
  }

  ngOnInit(): void {
    this.loadVelocities();
    this.getChartData();
  }

  loadVelocities(): void {
    this.kpiService.getSprints().subscribe((data) => {
      this.velocities = data;

      // Extraire les années disponibles à partir des données
      this.years = Array.from(
        new Set(this.velocities.map(v => new Date(v.startDate).getFullYear().toString()))
      ).sort();

      this.years.unshift('All'); // Ajouter "All" en tête de liste
      this.filterData(); // Appliquer le filtre initial
    });
  }

  filterData(): void {
    if (this.selectedYear === 'All') {
      this.filteredVelocities = [...this.velocities];
    } else {
      this.filteredVelocities = this.velocities.filter(v => {
        const year = new Date(v.startDate).getFullYear().toString();
        return year === this.selectedYear;
      });
    }

    this.updateChart(); // Mettre à jour le graphique après filtrage
  }

  createChart(): void {
    if (typeof document !== 'undefined') {
      const ctx = document.getElementById('myChart') as HTMLCanvasElement;
  
      this.chart3 = new Chart(ctx, {
        type: 'bar',
        data: this.getChartData(),
        options: {
          scales: {
            y: {
              beginAtZero: true
            }
          },
          plugins: {
            datalabels: {
              display: false 
            }
          }
        }
      });
    }
  }
  
  updateChart(): void {
    if (this.chart3) {
      this.chart3.data = this.getChartData();
      this.chart3.update();
    } else {
      this.createChart();
    }
  }

  getChartData() {
    const sprintNames = this.filteredVelocities
      .map(v => v.name.replace(/^Tableau\s/, ''))
      .sort((a, b) => a.localeCompare(b, undefined, { numeric: true }));
      this.sprintData=sprintNames.map(name =>
        this.velocities.find(v => v.name.replace(/^Tableau\s/, '') === name)
      );
    const completedStoryPoints = this.sprintData.map(v => v.completedStoryPoints || 0);
    const totalStoryPoints = this.sprintData.map(v => v.estimatedStoryPoints || 0);

    return {
      labels: sprintNames,
      datasets: [
        {
          label: 'Completed Story Points',
          data: completedStoryPoints,
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        },
        {
          label: 'Total Story Points',
          data: totalStoryPoints,
          backgroundColor: 'rgba(153, 102, 255, 0.2)',
          borderColor: 'rgba(153, 102, 255, 1)',
          borderWidth: 1
        }
      ]
    };
  }
}
