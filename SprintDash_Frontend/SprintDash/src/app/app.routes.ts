import { Routes } from '@angular/router';
import { LoginComponent } from './Components/auth/login/login.component';
import { SignupComponent } from './Components/auth/signup/signup.component';
import { HomeComponent } from './Components/home/home.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { KpisEvolutionComponent } from './Components/kpis-evolution/kpis-evolution.component';
import { SprintsTableComponent } from './Components/sprints-table/sprints-table.component';
import { UserProfileComponent } from './Components/user-profile/user-profile.component';

export const routes: Routes = [

    { path: 'login', component: LoginComponent },
    { path: 'signup', component: SignupComponent },
    { path: 'home', component: HomeComponent},
    {path: 'dashboard',component: DashboardComponent},
  {path: 'user-profile',component: UserProfileComponent},
  {path: 'sprints-table',component: SprintsTableComponent },
  {path: 'kpis-evolution',component: KpisEvolutionComponent},
    {path: '', redirectTo: 'home' , pathMatch: 'full'}


];
