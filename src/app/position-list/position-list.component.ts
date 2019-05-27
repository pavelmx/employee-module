import { Component, OnInit } from '@angular/core';
import { PositionService } from '../services/position.service';
import { Position } from '../models/position.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-position-list',
  templateUrl: './position-list.component.html',
  styleUrls: ['./position-list.component.css']
})
export class PositionListComponent implements OnInit {

  list: Position[];
  form: any={};
  formedit: any={};

  constructor(
    private positionService: PositionService
    ) { }

  ngOnInit() {    
    this.initPositionList();
  }


  initPositionList() {
    this.positionService.getList()
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
}
