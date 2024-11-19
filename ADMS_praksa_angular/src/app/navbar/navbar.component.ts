import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../shared/auth-services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private http: HttpClient,private authService:AuthService,private router:Router) { }

  ngOnInit(): void {
  }
  logout() {
    this.authService.logout();  
    this.router.navigate(['/login']);
  }
  

}
