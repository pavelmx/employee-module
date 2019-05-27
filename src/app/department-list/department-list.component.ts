import { Component, OnInit } from '@angular/core';
import { Department } from '../models/department.model';
import { DepartmentService } from '../services/department.service';
import { Employee } from '../models/employee.model';
import { EmployeeService } from '../services/employee.service';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent implements OnInit {

  list: Department[];
  form: any={};
  formedit: any={};
  employeeList: Employee[];

  constructor(
    private departmentService: DepartmentService,
    private employeeService: EmployeeService
    ) { }

  ngOnInit() {
    this.initDepartmentList();
    this.initEmployeeList();
  }

  initDepartmentList(){
    this.departmentService.getList()
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

  initEmployeeList(){
    this.employeeService.getList()
    .subscribe( 
      response => {
        this.employeeList = response; 
        console.log(this.list);       
      },
      error => {
        console.log(error);
      }      
     );
  }

  setEditDepartment(department: Department){
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

}
