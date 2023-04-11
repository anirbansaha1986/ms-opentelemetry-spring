import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  API = 'http://localhost:9090';
  addressAPI = 'http://localhost:9091';

  public registerStudent(studentData: any) {
    return this.http.post(this.API + '/registerStudent', studentData);
  }

  public getAddresses() {
    return this.http.get(this.addressAPI + '/')
  }

  public getStudents() {
    return this.http.get(this.API + '/getStudents');
  }

  public getStudent(id: any) {
    return this.http.get(this.API + '/getById?id=' + id);
  }

  public deleteStudent(id: any) {
    return this.http.delete(this.API + '/deleteStudent?id=' + id);
  }

  public updateStudents(student: any) {
    return this.http.put(this.API + '/updateStudent', student);
  }

}
