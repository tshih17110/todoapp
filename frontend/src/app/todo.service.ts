import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task, Progress } from './models/task.model';


@Injectable({
  providedIn: 'root'
})
export class TodoService {

  private apiUrl = 'http://localhost:8080/api/tasks';

  constructor(private http: HttpClient) { }

  // Welcome message
  getWelcome(): Observable<HttpResponse<string>> {
    return this.http.get<string>(this.apiUrl + '/welcome', { observe: 'response'})
  }

  // Get all tasks
  getAllTasks(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl + '/')
  }

  // Get all tasks with specified progress
  getByTaskProgress(progress: Progress): Observable<Task[]> {
    const params = new HttpParams().set('progress', progress);
    return this.http.get<Task[]>(this.apiUrl + '/progress', { params });
  }

  // Create new task
  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(this.apiUrl + '/create', task);
  }

  updateTask(id: number, task: Task): Observable<Task> {
    return this.http.put<Task>(this.apiUrl + '/' + id, task);
  }

}
