import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { PositionService } from '../services/position.service';
import { Position } from '../models/position.model';
import { NgForm } from '@angular/forms';
import { NgbdSortableHeader, SortEvent } from '../util/sortable.directive';

@Component({
  selector: 'app-position-list',
  templateUrl: './position-list.component.html',
  styleUrls: ['./position-list.component.css']
})
export class PositionListComponent implements OnInit {

  list: Position[];
  form: any={};
  formedit: any={};
  page: number = 0;
  size: number = 5;
  column: string = 'id';
  order: string = '';
  length: number;
  lastPage: number;


  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  
  constructor(
    private positionService: PositionService
    ) { }

  ngOnInit() {    
    this.initPositionList();
  }


  initPositionList() {
    this.positionService.getPageableList(this.page, this.size, this.column, this.order)
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

  setEditPosition(position: Position){
    this.positionService.getOne(position.id)
    .subscribe(
      response => {
        console.log(response);
        this.formedit = response;
      }
    );
  }

  add(form: NgForm) {
    var position = new Position();
    position = this.form;
    this.positionService.add(position)
      .subscribe(
        response => {
          console.log(response)
          this.initPositionList();
          form.resetForm();
        },
        error => {
          console.log(error)
        }
      );
  }

  edit(form: NgForm) {
    this.positionService.edit(this.formedit)
      .subscribe(
        response => {
          console.log(response)
          this.initPositionList();
          form.resetForm();
        },
        error => {
          console.log(error)
        }
      );
  }

  changeStatus(newstatus: number, id: number){
    if(newstatus === 0){
      this.positionService.disable(id)
      .subscribe(
        response => {
          this.initPositionList();
        },
        error => {
          this.initPositionList();
          console.log(error)
        }
      );      
    }
    if(newstatus === 1){
      this.positionService.enable(id)
      .subscribe(
        response => {
          this.initPositionList();
        },
        error => {
          this.initPositionList();
          console.log(error)
        }
      );   
    }
  }

  onPageChange(pageNumber){   
    
      this.page = pageNumber - 1;
    
    this.initPositionList();
  }

  onSizeChange(){
    this.page = 0; 
    this.initPositionList();
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
    this.initPositionList();
  }
}
