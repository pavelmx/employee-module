import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomeService } from '../services/home.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  listData: string[]=[];
  logged: boolean;
  token: string ='';

  constructor( private homeService: HomeService) { }

  ngOnInit() {
    console.log("start token " + localStorage.getItem('access_token'));
    if(localStorage.getItem('access_token')!=null){
      this.logged = true;
      this.token = localStorage.getItem('access_token');
      this.getList();
    }else{
      this.logged = false;
    }    
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
    localStorage.clear();     
  }


  print(){    
    console.log(localStorage.getItem("access_token"))
  }
}
