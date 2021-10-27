import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebRequestService {
ROOT_URI = "http://localhost:4200"
  constructor(private http: HttpClient) { }
exampleRequest(){
  return this.http.get(this.ROOT_URI);
}



}
