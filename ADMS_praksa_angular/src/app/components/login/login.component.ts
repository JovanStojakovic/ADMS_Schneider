import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { LoginRequest } from './login-request';
import { AuthService } from 'src/app/shared/auth-services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({})
  loginRequest!: LoginRequest
  showAlert: boolean = false;
  showAlertSuccess: boolean = false

  constructor(private authService: AuthService, private router: Router) {
    this.loginRequest ={
      username: "",
      password: ""
    }
   }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(""),
      password: new FormControl("")
    })
  }
  login(){
    this.loginRequest.username = this.loginForm.get('username')?.value;
    this.loginRequest.password = this.loginForm.get('password')?.value;
 
    this.authService.login(this.loginRequest)
    .subscribe(response => {
      const token =  JSON.parse(response);
      console.log(token.token)
      localStorage.setItem('token', token.token);
      this.showAlertSuccess = true;



    },    (error: HttpErrorResponse) => {
      if (error) {
        this.showAlert = true;

      } 

       });
  }
  closeAlert() {
    this.showAlert = false;
  }
  closeAlertSuccess() {
    this.showAlertSuccess = false;
    this.router.navigate(['/operatorPage']);

  }

}
