
export interface Sprint {
  id: number;
  name: string;
  startDate?:string; // Date or string depending on your use case
  endDate?:string; // Date or string depending on your use case
  state?: string;
  estimatedStoryPoints?: number;
  completedStoryPoints?: number;
  
}

export interface Kpi {
  id?: number;
  period?: string; // Optional, used for velocity calculations
  totalSprints?: number;
  totalStoryPoints?: number;
  totalSprintsCompleted?: number;
  totalTickets?: number;
  totalTicketsCompleted?: number;
  failureRate?: number;
  totalBugs?: number;
  bugsResolved?: number;
  bugsOpened?: number;
  totalStoryPointsCompleted?: number;
  year? : string;
  semester? : string;
  velocity? : number
}


export interface Member {
  id: number;
  name: string | null;
  email: string;
  password: string | null;
  phoneNumber: string | null;
  birthDate: string | null; // ou Date | null si tu comptes le parser
  gender: string | null;
}

export interface MemberStats {
  id: number;
  scrumMember: Member;
  totalStoryPoints: number;
  tasksToDo: number;
  tasksCompleted: number;
}


