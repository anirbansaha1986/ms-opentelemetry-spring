import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MentorService {

  constructor(private http: HttpClient) { }

  API = environment.apiUrl + ':9090/api/mentor';
  registerAPI = environment.apiUrl + ':8083/api/mentor';

  public registerMentor(mentorData: any) {
    return this.http.post(this.registerAPI + '/registerMentor', mentorData);
  }
  public getMentors() {
    return this.http.get(this.API + '/getMentors');
  }

  public getMentor(id: any) {
    return this.http.get(this.API + '/getById?id=' + id);
  }

  public updateMentor(mentor: any) {
    return this.http.put(this.registerAPI + '/updateMentor', mentor);
  }
}
