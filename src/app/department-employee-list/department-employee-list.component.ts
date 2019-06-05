import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { DepartmentEmployee } from '../models/department-employee.model';
import { DepartmentEmployeeService } from '../services/department-employee.service';
import { NgbdSortableHeader, SortEvent } from '../util/sortable.directive';
import { from } from 'rxjs';


@Component({
  selector: 'app-department-employee-list',
  templateUrl: './department-employee-list.component.html',
  styleUrls: ['./department-employee-list.component.css']
})
export class DepartmentEmployeeListComponent implements OnInit {

  list: DepartmentEmployee[];
  page: number = 0;
  size: number = 5;
  column: string = 'id';
  order: string = 'asc';
  length: number;
  lastPage: number;


  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;


  constructor(
    private departmentEmployeeService: DepartmentEmployeeService
  ) { }

  ngOnInit() {
    this.initDepartmentEmployee();
  }


  
  initDepartmentEmployee() {
    this.departmentEmployeeService.getPageableList(this.page, this.size, this.column, this.order)
      .subscribe(
        response => {
          this.list = response['content']; 
        this.length = response['totalElements'];
        this.lastPage = response['totalPages']; 
          console.log(this.list);
        },
        error => {
          console.log(error);
        }
      );
  }

  onPageChange(pageNumber){   
   
      this.page = pageNumber - 1;
    
    this.initDepartmentEmployee();
  }

  onSizeChange(){
    this.page = 0;   
    this.initDepartmentEmployee();
  }
 
  onSort({column, direction}: SortEvent) {
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });
    this.page = 0;
    this.column = column;
    this.order = direction;
    console.log(column)
    console.log(direction)
    this.initDepartmentEmployee();
  }
}
