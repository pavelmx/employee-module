import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { PositionEmployee } from '../models/position-employee.model';
import { PositionEmployeeService } from '../services/position-employee.service';
import { NgbdSortableHeader, SortEvent } from '../util/sortable.directive';

@Component({
  selector: 'app-position-employee-list',
  templateUrl: './position-employee-list.component.html',
  styleUrls: ['./position-employee-list.component.css']
})
export class PositionEmployeeListComponent implements OnInit {

  list: PositionEmployee[];
  page: number = 0;
  size: number = 5;
  column: string = 'id';
  order: string = '';
  length: number;
  lastPage: number;


  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;


  constructor(
    private positionEmployeeService: PositionEmployeeService
  ) { }

  ngOnInit() {
    this.initPositionEmployeeList();
  }

  initPositionEmployeeList() {
    this.positionEmployeeService.getPageableList(this.page, this.size, this.column, this.order)
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
    
    this.initPositionEmployeeList();
  }

  onSizeChange(){
    this.page = 0;   
    this.initPositionEmployeeList();
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
    this.initPositionEmployeeList();
  }
}
