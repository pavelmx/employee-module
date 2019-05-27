import { Component, OnInit } from '@angular/core';
import { HiringEmployeeInfo } from '../models/hiring-employee-info.model';
import { HiringEmployeeInfoService } from '../services/hiring-employee-info.service';

@Component({
  selector: 'app-hiring-info-list',
  templateUrl: './hiring-info-list.component.html',
  styleUrls: ['./hiring-info-list.component.css']
})
export class HiringInfoListComponent implements OnInit {

  list: HiringEmployeeInfo[];

  constructor(
    private hiringService: HiringEmployeeInfoService
  ) { }

  ngOnInit() {
    this.initHiringList();
  }

  initHiringList() {
    this.hiringService.getList()
      .subscribe(
        response => {
          this.list = response;
          console.log(this.list);
        },
        error => {
          console.log(error);
        }
      );
  }

}
