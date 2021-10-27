import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient:HttpClient) { }

  public loginUserFromRemote(user: User):Observable<any>{
   return this.httpClient.post<any>("localhost:8080/login",user)
  }
  public registerUserFromRemote(user: User):Observable<any>{
    return this.httpClient.post<any>("localhost:8080/registeruser",user)
   }
}
