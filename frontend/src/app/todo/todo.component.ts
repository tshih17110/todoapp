import { Component, OnInit } from '@angular/core';
import { TodoService } from '../todo.service';
import { Task, Progress } from '../models/task.model'
import { Observer } from 'rxjs';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['../../styles/main.scss']
})
export class TodoComponent implements OnInit {

  tasks: Task[] = [];
  progressValues = Object.values(Progress);
  Progress = Progress;
  newTask: Task = {task: '', progress: Progress.INCOMPLETE };

  constructor(private todoService: TodoService) { }

  ngOnInit(): void {
    this.getTasks();
  }

  getTasks(): void {
    this.todoService.getAllTasks().subscribe(tasks => {
      this.tasks = tasks;
    });
  }

  createTask(newTask: Task) {
    newTask.progress = newTask.progress || Progress.INCOMPLETE; 
    this.tasks.push(newTask);
    this.newTask = {
      task: '',
      progress: Progress.INCOMPLETE
    };
  }

  updateTask(id: number, task: Task): void {
    this.todoService.updateTask(id, task).subscribe(updatedTask => {
      const index = this.tasks.findIndex(t => t.id === id);
      if (index !== -1) {
        this.tasks[index] = updatedTask;
      }
    });
  }

  deleteTask(id: number): void {
    this.todoService.deleteTask(id).subscribe(() => {
      this.tasks = this.tasks.filter(t => t.id !== id);
    });
  }

  changeProgress(task: Task): void {
    switch (task.progress) {
      case Progress.INCOMPLETE:
        task.progress = Progress.ONGOING;
        console.log("Progress change to ongoing");
        break;
      case Progress.ONGOING:
        task.progress = Progress.COMPLETE;
        console.log("Progress change to complete");
        break;
      case Progress.COMPLETE:
        task.progress = Progress.INCOMPLETE;
        console.log("Progress change to incomplete");
        break;
      default:
        break;
    }
    if (task.id) {
      const observer: Observer<Task> = {
        next: updatedTask => {
          console.log("Task updated successfully: ", updatedTask);
        },
        error: error => {
          console.error("Error updating task: ", error);
        },
        complete: () => {}
      };
      this.todoService.updateTask(task.id, task).subscribe(observer);
    }
  }

  getProgressColor(progress: Progress): string {
    switch (progress) {
      case Progress.INCOMPLETE:
        return 'incomplete-color';
      case Progress.ONGOING:
        return 'ongoing-color';
      case Progress.COMPLETE:
        return 'complete-color';
      default:
        return '';
    }
  }

}
