import { Component, OnInit } from '@angular/core';
import { DepartmentEmployee } from '../models/department-employee.model';
import { DepartmentEmployeeService } from '../services/department-employee.service';

@Component({
  selector: 'app-department-employee-list',
  templateUrl: './department-employee-list.component.html',
  styleUrls: ['./department-employee-list.component.css']
})
export class DepartmentEmployeeListComponent implements OnInit {

  list: DepartmentEmployee[];

  constructor(
    private departmentEmployeeService: DepartmentEmployeeService
  ) { }

  ngOnInit() {
    this.initHiringList();
  }

  initHiringList() {
    this.departmentEmployeeService.getList()
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
