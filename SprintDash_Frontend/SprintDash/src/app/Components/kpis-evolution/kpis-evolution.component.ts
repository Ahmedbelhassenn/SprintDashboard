import { Component } from '@angular/core';
import { VelocityPerSemesterChartComponent } from './velocity-per-semester-chart/velocity-per-semester-chart.component';
import { VelocityPerYearChartComponent } from './velocity-per-year-chart/velocity-per-year-chart.component';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
    selector: 'app-kpis-evolution',
    templateUrl: './kpis-evolution.component.html',
    standalone: true,
    imports: [VelocityPerSemesterChartComponent, VelocityPerYearChartComponent, RouterLink, RouterLinkActive],
    styleUrl: './kpis-evolution.component.css'
})
export class KpisEvolutionComponent {

}
