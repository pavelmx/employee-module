import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HomeService } from '../services/home.service';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {

  listData: string[]=[];
  logged: boolean;
  token: string ='';

  constructor( private homeService: HomeService,
    private cookieService: CookieService) { }

  ngOnInit() {
    this.token = this.cookieService.get('access_token');
    this.getList();
    this.cookieService.set('test', 'test');
  }

  getList(){
    this.homeService.getList(this.token)
    .subscribe(data =>{
      this.listData = data;
    },
    error => {
      console.log(error)
    }
    );
  }

  logout(){
    //window.location.reload();
    window.location.href = "http://localhost:8080";
    this.cookieService.delete('access_token');
  }

}
