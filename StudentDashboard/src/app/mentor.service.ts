import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MentorService {

  constructor(private http: HttpClient) { }

  API = 'http://localhost:9090/api/mentor';

  public registerMentor(mentorData: any) {
    return this.http.post(this.API + '/registerMentor', mentorData);
  }
  public getMentors() {
    return this.http.get(this.API + '/getMentors');
  }

  public getMentor(id: any) {
    return this.http.get(this.API + '/getById?id=' + id);
  }

  public updateMentor(mentor: any) {
    return this.http.put(this.API + '/updateMentor', mentor);
  }
}
