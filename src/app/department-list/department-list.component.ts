import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { Department } from '../models/department.model';
import { DepartmentService } from '../services/department.service';
import { Employee } from '../models/employee.model';
import { EmployeeService } from '../services/employee.service';
import { NgForm, FormControl } from '@angular/forms';
import { NgbdSortableHeader, SortEvent } from '../util/sortable.directive';



@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent implements OnInit {

  list: Department[];
  length: number;
  lastPage: number;
  form: any = {};
  formedit: any = {};
  employeeList: Employee[];
  page: number = 0;
  size: number = 5;
  column: string = '';
  order: string = '';
  checkOrder: boolean;

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  constructor(
    private departmentService: DepartmentService,
    private employeeService: EmployeeService
  ) {
  }

  ngOnInit() {
    this.initDepartmentList();
    this.initEmployeeList();
  }

  initDepartmentList() {
    this.departmentService.getPageableList(this.page, this.size, this.column, this.order)
      .subscribe(
        response => {
          this.list = response['content'];
          this.length = response['totalElements'];
          this.lastPage = response['totalPages'];
          console.log(response);
        },
        error => {
          console.log(error);
        }
      );
  }

  initEmployeeList() {
    this.employeeService.getList()
      .subscribe(
        response => {
          this.employeeList = response;
          //console.log(this.employeeList);       
        },
        error => {
          console.log(error);
        }
      );
  }

  setEditDepartment(department: Department) {
    this.departmentService.getOne(department.id)
      .subscribe(
        response => {
          console.log(response);
          this.formedit = response;
        }
      );
  }

  add(form: NgForm) {
    var department = new Department();
    department = this.form;
    console.log(this.departmentService);
    this.departmentService.add(department, this.form.manager_id)
      .subscribe(
        response => {
          console.log(response)
          this.initDepartmentList();
          form.resetForm();
        },
        error => {
          console.log(error)
        }
      );
  }

  edit(form: NgForm) {
    this.employeeService.getOne(this.formedit.manager_id)
      .subscribe(
        response => {
          console.log(response)
          this.formedit.manager = response;
          this.departmentService.edit(this.formedit)
            .subscribe(
              response => {
                console.log(response)
                this.initDepartmentList();
                form.resetForm();
              },
              error => {
                console.log(error)
              }
            );
        },
        error => {
          console.log(error)
        }
      );
  }

  onPageChange(pageNumber) {   
    this.page = pageNumber-1;
    console.log("change page on " + this.page)
    this.initDepartmentList();
  }

  onSizeChange() {
    console.log("last page " + this.lastPage)
    console.log("change size page on " + this.page)
    this.page = 0;//////solve last page
    this.initDepartmentList();
  }

  sort(column: string){
    this.column = column;
    this.checkOrder = !this.checkOrder;
    if(this.checkOrder){
      this.order = 'asc';
    }else{
      this.order = 'desc';
    }
    this.initDepartmentList();
  }

  onSort({ column, direction }: SortEvent) {
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });
    this.page = 0;
    this.column = column;
    this.order = direction;
    console.log(this.column + " " + this.order)
    console.log(this.page)
    console.log(this.size)
    this.initDepartmentList();
  }

}
