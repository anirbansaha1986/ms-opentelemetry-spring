import { Component, ElementRef, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { StudentService } from './student.service';
import { MentorService } from './mentor.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  @ViewChild('closeStudentModal')
  private closeStudentModal?: ElementRef;

  @ViewChild('mentorModal')
  private mentorModal?: ElementRef;

  @ViewChild('updatedForm', { static: false }) 
  updatedForm?: NgForm;

  studentModalTitle = 'Register Student Information';
  mentorModalTitle = 'Register Mentor Information'; 
  modalCreateStatus = true
  hasError = false
  errorMessage = 'Please provide all information';
  studentCount = 0;
  mentorCount = 0;
  studentDetails = null as any;
  mentorDetails = null as any;
  formSubmitTitle = 'Register'
  studentDetailsUpdate = {
    id: "",
    firstName: "",
    lastName: "",
    email:"",
    mentorId: "",
    mentorName: "",
    mentorEmail: ""
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

  createOrUpdateStudent() {
    if (this.modalCreateStatus) {
      this.studentService.registerStudent(this.studentDetailsUpdate).subscribe(
        (resp) => {
          console.log(resp);
          this.mentorDetails = resp;
          if (this.closeStudentModal)
          this.closeStudentModal.nativeElement.click(); 

        },
        (err) => {
          console.log(err);
          this.hasError = true
          this.errorMessage = "Failed to create Student. Please try again";
        }
      )
      }
    else {
      this.studentService.updateStudent(this.studentDetailsUpdate).subscribe(
        (resp) => {
          console.log(resp);
          this.mentorDetails = resp;

        },
        (err) => {
          console.log("failed to fecth data --- \n",err);
          this.hasError = true
          this.errorMessage = "Failed to update Student. Please try again";
        }
      )
    }
   
  }

  getStudentsDetails() {
    this.studentService.getStudents().subscribe(
      (resp) => {
        console.log(resp);
        this.studentDetails = resp;
        this.studentCount = this.studentDetails?.length
      },
      (err) => {
        console.log(err);
      }
    );

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
    this.hasError = false
    this.errorMessage = 'Please provide all information';
    if (event == 'create'){
      this.studentModalTitle = 'Register Student Information';
      this. studentDetailsUpdate = {
        id: "",
        firstName: "",
        lastName: "",
        email:"",
        mentorId: "",
        mentorName: "",
        mentorEmail: ""
      };
      this.formSubmitTitle = 'Register'
      this.modalCreateStatus = true
    }
    else {
      this.studentModalTitle = 'Update Student Information';
      this.studentDetailsUpdate = Object.assign({}, event);
      this.formSubmitTitle = 'Update'
      this.modalCreateStatus = false
    }
  }

  onChange(event: any) {
    this.studentDetailsUpdate.mentorName = event.firstName + " " + event.lastName
    this.studentDetailsUpdate.mentorId = event.mentorId
  }

  createOrUpdateMentor() {
    if (this.modalCreateStatus) {
      this.mentorService.registerMentor(this.mentorDetailsUpdate).subscribe(
        (resp) => {
          console.log(resp);
          this.mentorDetails = resp;

        },
        (err) => {
          console.log(err);
        }
      )
      }
    else {
      this.mentorService.updateMentor(this.mentorDetailsUpdate).subscribe(
        (resp) => {
          console.log(resp);
          this.mentorDetails = resp;

        },
        (err) => {
          console.log(err);
        }
      )
    }
    if (this.mentorModal)
      this.mentorModal.nativeElement.click(); 
  }

  getMentorDetails(){
    this.mentorService.getMentors().subscribe(
      (resp) => {
        console.log(resp);
        this.mentorDetails = resp;
        this.mentorCount = this.mentorDetails?.length
      },
      (err) => {
        console.log(err);
      }
    );
  }

  showModalMentorUpdate(event: any) {
    console.log(event);
    this.hasError = false
    this.errorMessage = 'Please provide all information';
    if (event == 'create'){
      this.mentorModalTitle = 'Register Mentor Information';
      this.mentorDetailsUpdate = this.mentorDetails;
      this.formSubmitTitle = 'Register'
      this.modalCreateStatus = true
    }
    else {
      this.mentorModalTitle = 'Update Mentor Information';
      this.mentorDetailsUpdate = Object.assign({}, event);
      this.formSubmitTitle = 'Update'
      this.modalCreateStatus = false
    }
  }
  
  
}


