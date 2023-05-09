import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { StudentService } from './student.service';
import { MentorService } from './mentor.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  studentModalTitle = 'Register Student Information';
  mentorModalTitle = 'Register Mentor Information'; 
  studentDetails = null as any;
  mentorDetails = null as any;
  formSubmitTitle = 'Register'
  studentDetailsUpdate = {
    id: "",
    firstName: "",
    lastName: "",
    email:"",
    mentorId: "",
    mentorName: ""
  }

  mentorDetailsUpdate = {
    id: "",
    firstName: "",
    lastName: "",
    email:"",

  }

  constructor(private studentService: StudentService, private mentorService: MentorService) { 
    this.getStudentsDetails();
    this.getMentorDetails();
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

  getMentorDetails(){
    this.mentorService.getMentors().subscribe(
      (resp) => {
        console.log(resp);
        this.mentorDetails = resp;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  createOrUpdateStudent() {
    this.studentService.registerStudent(this.studentDetailsUpdate).subscribe(
      (resp) => {
        console.log(resp);
        this.mentorDetails = resp;
      },
      (err) => {
        console.log(err);
      }
    )
  }
  deleteStudent(student: any) {
    this.studentService.deleteStudent(student.id).subscribe(
      (resp) => {
        console.log(resp);
        
      },
      (err) => {
        console.log(err);
      }

    )
  }
  showModalUpdate(event: any) {
    console.log(event);
    if (event == 'create'){
      this.studentModalTitle = 'Register Student Information';
      this. studentDetailsUpdate = {
        id: "",
        firstName: "",
        lastName: "",
        email:"",
        mentorId: "",
        mentorName: ""
      };
      this.formSubmitTitle = 'Register'
    }
    else {
      this.studentModalTitle = 'Update Student Information';
      this.studentDetailsUpdate = Object.assign({}, event);
      this.formSubmitTitle = 'Update'
    }
  }
  showModalMentorUpdate(event: any) {
    console.log(event);
    if (event == 'create'){
      this.mentorModalTitle = 'Register Mentor Information';
      this.mentorDetailsUpdate = this.mentorDetails;
      this.formSubmitTitle = 'Register'
    }
    else {
      this.mentorModalTitle = 'Update Mentor Information';
      this.mentorDetailsUpdate = Object.assign({}, event);
      this.formSubmitTitle = 'Update'
    }
  }
  createOrUpdateMentor() {
    throw new Error('Method not implemented.');
  }
  onChange(event: any) {
    this.studentDetailsUpdate.mentorName = event.firstName + " " + event.lastName
    this.studentDetailsUpdate.mentorId = event.mentorId
  }
}


