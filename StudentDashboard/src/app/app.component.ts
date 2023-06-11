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

  @ViewChild('closeMentorModal')
  private closeMentorModal?: ElementRef;

  @ViewChild('updatedForm', { static: false }) 
  updatedForm?: NgForm;

  studentModalTitle = 'Register Customer Information';
  mentorModalTitle = 'Register Consultant Information'; 
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

  getStudentsDetails() {
    this.studentService.getStudents().subscribe(
      (resp) => {
        console.log(resp);
        if(Array.isArray(resp)) {
          this.studentDetails = resp;
          this.studentCount = this.studentDetails?.length
        }
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
        if(Array.isArray(resp)) {
          this.studentDetails = resp;
          this.studentCount = this.studentDetails?.length
        }
        
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
      this.studentModalTitle = 'Register Customer Information';
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
      this.studentModalTitle = 'Update Customer Information';
      this.studentDetailsUpdate = Object.assign({}, event);
      this.formSubmitTitle = 'Update'
      this.modalCreateStatus = false
    }
  }

  onChange(event: any) {
    console.log("mentor name " + event.firstName + " " + event.lastName)
    console.log("mentor id " + event.id)
    this.studentDetailsUpdate.mentorName = event.firstName + " " + event.lastName
    this.studentDetailsUpdate.mentorId = event.id
  }

  createOrUpdateStudent() {
    if (this.modalCreateStatus) {
      this.studentService.registerStudent(this.studentDetailsUpdate).subscribe(
        (resp) => {
          console.log(resp);
          if(Array.isArray(resp)) {
            console.log("student return type array");
            this.studentDetails = resp;
            this.studentCount = this.studentDetails?.length
          }
          else {
            console.log("student return type object");
            this.getStudentsDetails();
          }
          if (this.closeStudentModal)
          this.closeStudentModal.nativeElement.click(); 

        },
        (err) => {
          console.log(err);
          this.hasError = true
          this.errorMessage = "Failed to create Customer. Please try again";
        }
      )
      }
    else {
      this.studentService.updateStudent(this.studentDetailsUpdate).subscribe(
        (resp) => {
          console.log(resp);
          if(Array.isArray(resp)) {
            console.log("student return type array");
            this.studentDetails = resp;
            this.studentCount = this.studentDetails?.length
          }
          else {
            console.log("student return type object");
            this.getStudentsDetails();
          }
          if (this.closeStudentModal)
          this.closeStudentModal.nativeElement.click(); 

        },
        (err) => {
          console.log("failed to fecth data --- \n",err);
          this.hasError = true
          this.errorMessage = "Failed to update Customer. Please try again";
        }
      )
    }
   
  }

  createOrUpdateMentor() {
    if (this.modalCreateStatus) {
      console.log("enter mentor create section");
      this.mentorService.registerMentor(this.mentorDetailsUpdate).subscribe(
        (resp) => {
          console.log(resp);
          if(Array.isArray(resp)) {
            console.log("mentor return type array");
            this.mentorDetails = resp;
            this.mentorCount = this.mentorDetails?.length
          }
          else {
            console.log("mentor return type object");
            this.getMentorDetails();
          }

          if (this.closeMentorModal)
            this.closeMentorModal.nativeElement.click(); 
        },
        (err) => {
          console.log(err);
          this.hasError = true
          this.errorMessage = "Failed to create Consultant. Please try again";
        }
      )
      }
    else {
      console.log("enter mentor update section");
      this.mentorService.updateMentor(this.mentorDetailsUpdate).subscribe(
        (resp) => {
          console.log(resp);
          if(Array.isArray(resp)) {
            console.log("mentor return type array");
            this.mentorDetails = resp;
            this.mentorCount = this.mentorDetails?.length
          }
          else {
            console.log("mentor return type object");
            this.getMentorDetails();
          }

          if (this.closeMentorModal)
            this.closeMentorModal.nativeElement.click(); 
          this.getStudentsDetails();

        },
        (err) => {
          console.log(err);
          this.hasError = true
          this.errorMessage = "Failed to update Consultant. Please try again";
        }
      )
    }

  }

  getMentorDetails(){
    this.mentorService.getMentors().subscribe(
      (resp) => {
        console.log(resp);
        if(Array.isArray(resp)) {
          console.log("mentor return type array");
          this.mentorDetails = resp;
          this.mentorCount = this.mentorDetails?.length
        }
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
      this.mentorModalTitle = 'Register Consultant Information';
      this. mentorDetailsUpdate = {
        id: "",
        firstName: "",
        lastName: "",
        email:""
      };
      this.formSubmitTitle = 'Register'
      this.modalCreateStatus = true
    }
    else {
      this.mentorModalTitle = 'Update Consultant Information';
      this.mentorDetailsUpdate = Object.assign({}, event);
      this.formSubmitTitle = 'Update'
      this.modalCreateStatus = false
    }
  }
}


