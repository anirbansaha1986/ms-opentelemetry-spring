import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpErrorHandler, HandleError } from './http-error-handler.service';
import { environment } from './../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private handleError: HandleError;

  constructor(private http: HttpClient,
    httpErrorHandler: HttpErrorHandler) {
      this.handleError = httpErrorHandler.createHandleError('StudentService');
   }

  API = environment.apiUrl + ':9090/api/student'; // 'http://localhost:8081/api/student'// environment.apiUrl + '/api/student';
  registerAPI = environment.apiUrl + ':8081/api/student';
  public registerStudent(studentData: any) {
    return this.http.post(this.registerAPI + '/registerStudent', studentData);
  }

  public getStudents() {
    return this.http.get(this.API + '/getStudents');
  }

  public getStudentById(id: any) {
    return this.http.get(this.API + '/getById?id=' + id);
  }

  public deleteStudent(id: any) {
    return this.http.delete(this.registerAPI + '/deleteStudent?id=' + id);
  }

  public updateStudent(student: any) {
    return this.http.put(this.registerAPI + '/updateStudent', student);
  }

}
