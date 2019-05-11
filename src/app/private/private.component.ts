import { Component, OnInit } from '@angular/core';
import { HomeService } from '../services/home.service';

@Component({
  selector: 'app-private',
  templateUrl: './private.component.html',
  styleUrls: ['./private.component.css']
})
export class PrivateComponent implements OnInit {

  listData: string[]=[];


  constructor(private homeService: HomeService) { }

  ngOnInit() {
    this.getList();
  }

  getList(){
    this.homeService.getList()
    .subscribe(data =>{
      this.listData = data;
    },
    error => {
      console.log(error)
    }
    );
  }

}
