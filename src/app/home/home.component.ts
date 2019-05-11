import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private cookieService: CookieService) { }

  ngOnInit() {
  }

  logout(){
    window.location.href = "http://localhost:8080/auth/home";
    this.cookieService.delete('access_token');
  }

}
