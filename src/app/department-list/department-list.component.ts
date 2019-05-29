import { Component, OnInit, ViewChildren, QueryList, ViewChild, ElementRef } from '@angular/core';
import { Department } from '../models/department.model';
import { DepartmentService } from '../services/department.service';
import { Employee } from '../models/employee.model';
import { EmployeeService } from '../services/employee.service';
import { NgForm, FormControl } from '@angular/forms';
import { NgbdSortableHeader, SortEvent } from '../util/sortable.directive';
import { ToastService } from '../util/toast.service';
import { ToasterService, Toast } from 'angular2-toaster';



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
  column: string = 'id';
  order: string = 'asc';
  checkOrder: boolean;
  errorMessage: string = '';

  @ViewChild('close_add') closeAddModal: ElementRef;
  @ViewChild('close_edit') closeEditModal: ElementRef;
  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  constructor(
    private departmentService: DepartmentService,
    private employeeService: EmployeeService,
    private toast: ToastService
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
          this.errorMessage = error.error.message;
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
    this.departmentService.add(department, this.form.manager_id)
      .subscribe(
        response => {
          console.log(response)
          this.initDepartmentList();
          this.closeAddModal.nativeElement.click();
          form.resetForm();
          this.toast.showSuccess("Success","New department '" + response.name + "' added");
        },
        error => {
          console.log(error);
          this.toast.showError("Error", error.error.message);
        }
      );
  }

  edit(form: NgForm) {
    var department = new Department();
    department = this.formedit;
    this.departmentService.edit(department, this.formedit.manager_id)
      .subscribe(
        response => {
          console.log(response)
          this.page = this.page;
          this.initDepartmentList();
          this.closeEditModal.nativeElement.click();
          //form.resetForm();
          this.toast.showSuccess("Success","Department '" + response.name + "' updated");
        },
        error => {
          console.log(error);
          this.toast.showError("Error", error.error.message);
        }
      );
  }

  onPageChange(pageNumber) {
    this.page = pageNumber - 1;
    console.log("change page on " + this.page)
    this.initDepartmentList();
  }

  onSizeChange() {
    console.log("last page " + this.lastPage)
    console.log("change size page on " + this.page)
    this.page = 0;//////solve last page
    this.initDepartmentList();
  }

  sort(column: string) {
    this.column = column;
    this.checkOrder = !this.checkOrder;
    if (this.checkOrder) {
      this.order = 'asc';
    } else {
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
