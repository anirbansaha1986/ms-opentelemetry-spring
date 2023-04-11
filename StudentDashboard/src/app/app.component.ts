import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { StudentService } from './student.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  modalTitle = 'Register Student Information';
  cities = []
  studentDetails = null as any;
  studentDetailsUpdate = {
    firstName: "",
    lastName: "",
    email:"",
    street: "",
    city: "",
    addressId: ""
  }

  constructor(private studentService: StudentService) { 
    this.getStudentsDetails();
  }
  
  register(params: NgForm) {
    this.studentService.registerStudent(params.value).subscribe(
      (Response) => {
        console.log(Response);
      },
      (err) => {
        console.log(err);
      }
    );

    this.studentService.registerStudent(params.value).subscribe({
      next: (Response) => console.log(Response),
      error: (err) => console.log(err)
    });
  }

  getStudentsDetails() {
    this.studentService.getStudents().subscribe(
      (resp) => {
        console.log(resp);
        this.studentDetails = resp;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  createOrUpdateStudent() {
    throw new Error('Method not implemented.');
  }
  deleteStudent(_t46: any) {
    throw new Error('Method not implemented.');
  }
  showModalUpdate(event: any) {
    console.log(event);
    if (event == 'create'){
      this.modalTitle = 'Register Student Information';
    }
    else {
      this.modalTitle = 'Update Student Information';
      this.  studentDetailsUpdate = event;
    }
  }
  createOrUpdateMentor() {
    throw new Error('Method not implemented.');
  }
}
