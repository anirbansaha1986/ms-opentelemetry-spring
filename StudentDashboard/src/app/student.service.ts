import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpErrorHandler, HandleError } from './http-error-handler.service';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private handleError: HandleError;

  constructor(private http: HttpClient,
    httpErrorHandler: HttpErrorHandler) {
      this.handleError = httpErrorHandler.createHandleError('StudentService');
   }

  API = 'http://localhost:9090/api/student';

  public registerStudent(studentData: any) {
    return this.http.post(this.API + '/registerStudent', studentData).pipe(
      catchError(this.handleError('registerStudent', studentData))
    );
  }

  public getStudents() {
    return this.http.get(this.API + '/getStudents').pipe(
      catchError(this.handleError('getStudents',[]))
    );
  }

  public getStudentById(id: any) {
    return this.http.get(this.API + '/getById?id=' + id).pipe(
      catchError(this.handleError('getStudents',[]))
    );
  }

  public deleteStudent(id: any) {
    return this.http.delete(this.API + '/deleteStudent?id=' + id).pipe(
      catchError(this.handleError('deleteStudent',id))
    );
  }

  public updateStudent(student: any) {
    return this.http.put(this.API + '/updateStudent', student).pipe(
      catchError(this.handleError('updateStudent',student))
    );
  }

}
