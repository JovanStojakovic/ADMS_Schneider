import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from 'src/app/components/login/login-request';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl

  constructor(private httpClient: HttpClient) { }

  login(loginRequest: LoginRequest): Observable <any>{
    return this.httpClient.post(`${this.apiUrl}/auth/login`, loginRequest, {responseType: 'text'})
   }

  isLogged(): boolean{
    const token = localStorage.getItem("token")
    if(token){
      return true
    }
    return false;
  }

  logout(){
    localStorage.removeItem("token")
  }
  getJwtToken (){
    return localStorage.getItem("token")
  }
}
