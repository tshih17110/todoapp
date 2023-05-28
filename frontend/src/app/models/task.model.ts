export interface Task {
    id: number;
    task: string;
    progress: Progress;
  }
  
  export enum Progress {
    INCOMPLETE = 'INCOMPLETE',
    ONGOING = 'ONGOING',
    COMPLETE = 'COMPLETE'
  }
  