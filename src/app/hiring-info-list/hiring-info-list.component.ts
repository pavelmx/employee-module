import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { HiringEmployeeInfo } from '../models/hiring-employee-info.model';
import { HiringEmployeeInfoService } from '../services/hiring-employee-info.service';
import { NgbdSortableHeader, SortEvent } from '../util/sortable.directive';

@Component({
  selector: 'app-hiring-info-list',
  templateUrl: './hiring-info-list.component.html',
  styleUrls: ['./hiring-info-list.component.css']
})
export class HiringInfoListComponent implements OnInit {

  list: HiringEmployeeInfo[];
  page: number = 0;
  size: number = 5;
  column: string = 'id';
  order: string = 'asc';
  length: number;
  lastPage: number;


  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;


  constructor(
    private hiringService: HiringEmployeeInfoService
  ) { }

  ngOnInit() {
    this.initHiringList();
  }

  initHiringList() {
    this.hiringService.getPageableList(this.page, this.size, this.column, this.order)
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
    
    this.initHiringList();
  }

  onSizeChange(){
    this.page = 0;   
    this.initHiringList();
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
    this.initHiringList();
  }
}
