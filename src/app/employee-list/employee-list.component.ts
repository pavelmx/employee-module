import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { Employee } from '../models/employee.model';
import { PersonalInfo } from '../models/personal-info.model';
import { Department } from '../models/department.model';
import { PositionService } from '../services/position.service';
import { DepartmentService } from '../services/department.service';
import { NgForm } from '@angular/forms';
import { Position } from '../models/position.model';
import { PositionEmployee } from '../models/position-employee.model';
import { DepartmentEmployee } from '../models/department-employee.model';
import { SortEvent, NgbdSortableHeader } from '../util/sortable.directive';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  list: Employee[];
  currentPositionEmployee: PositionEmployee;
  currentPosition: Position;
  currentDepartmentEmployee: DepartmentEmployee;
  currentDepartment: Department;
  positionList: Position[];
  departmentList: Department[];
  form: any = {};
  formedit: any = {};
  formchange: any = {};
  personalInfo: any = {};
  page: number = 0;
  size: number = 5;
  column: string = 'id';
  order: string = '';
  length: number;
  lastPage: number;


  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;


  constructor(
    private employeeService: EmployeeService,
    private positionService: PositionService,
    private departmentService: DepartmentService) { }

  ngOnInit() {
    this.initDepartmentList();
    this.initEmployeeList();
    this.initPositionList();
  }

  initEmployeeList() {
    this.employeeService.getPageableList(this.page, this.size, this.column, this.order)
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

  initPositionList() {
    this.positionService.getEnableList()
      .subscribe(
        response => {
          this.positionList = response;
          console.log(this.positionList);
        },
        error => {
          console.log(error);
        }
      );
  }

  initDepartmentList() {
    this.departmentService.getList()
      .subscribe(
        response => {
          this.departmentList = response;
          console.log(this.departmentList);
        },
        error => {
          console.log(error);
        }
      );
  }

  add(form: NgForm) {
    var employee = new Employee();
    employee.personalInfo = this.form;
    console.log(employee)
    this.employeeService.add(employee, this.form.position_id, this.form.department_id)
      .subscribe(
        response => {
          console.log(response)
          this.initEmployeeList();
          form.resetForm();
        },
        error => {
          console.log(error)
        }
      );
  }

  setEditEmployee(employee: Employee) {
    this.employeeService.getOne(employee.id)
      .subscribe(
        response => {
          this.formedit = response;
          this.personalInfo = this.formedit.personalInfo;
        }
      );
  }

  edit(form: NgForm) {
    this.employeeService.edit(this.formedit)
      .subscribe(
        response => {
          console.log(response)
          this.initEmployeeList();
          form.resetForm();
        },
        error => {
          console.log(error)
        }
      );
  }

  dismissRecoveryEmployee(choosed: number, id: number) {
    if (choosed === 0) {
      this.employeeService.dismiss(id)
        .subscribe(
          response => {
            this.initEmployeeList();
          },
          error => {
            this.initEmployeeList();
            console.log(error)
          }
        );
    }
    if (choosed === 1) {
      this.employeeService.recovery(id)
        .subscribe(
          response => {
            this.initEmployeeList();
          },
          error => {
            this.initEmployeeList();
            console.log(error)
          }
        );
    }
  }

  setCurrentPosition(id: number) {
    this.employeeService.getCurrentPositionEmployee(id)
      .subscribe(
        response => {
          this.currentPositionEmployee = response;    
          this.formchange.currentPositionId = this.currentPositionEmployee.position.id;      
          this.formchange.currentPositionName = this.currentPositionEmployee.position.name;
          this.formchange.currentEmployeeId = this.currentPositionEmployee.employee.id;
        },
        error => {
          console.log(error)
        });
  }

  changePosition() {
    this.employeeService.changePosition( this.formchange.currentEmployeeId, this.formchange.position_id)
      .subscribe(
        response => {
          this.setCurrentPosition(this.formchange.currentEmployeeId);
        },
        error => {
          this.setCurrentPosition(this.formchange.currentEmployeeId);
          console.log(error)
        }
      );
  }

  setCurrentDepartment(id: number) {
    this.employeeService.getCurrentDepartmentEmployee(id)
      .subscribe(
        response => {
          this.currentDepartmentEmployee = response;       
          this.formchange.currentDepartmentId = this.currentDepartmentEmployee.department.id; 
          this.formchange.currentDepartmentName = this.currentDepartmentEmployee.department.name;
          this.formchange.currentEmployeeId = this.currentDepartmentEmployee.employee.id;
        },
        error => {
          console.log(error)
        });
  }

  changeDepartment() {
    this.employeeService.changeDepartment(this.formchange.currentEmployeeId, this.formchange.department_id)
      .subscribe(
        response => {
          this.setCurrentDepartment(this.formchange.currentEmployeeId);
        },
        error => {
          this.setCurrentDepartment(this.formchange.currentEmployeeId);
          console.log(error)
        }
      );
  }

  onPageChange(pageNumber){   
    this.page = pageNumber-1;   
    this.initEmployeeList(); 
    
  }

  onSizeChange(){
    this.page = 0;  
    this.initEmployeeList(); 
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
    this.initEmployeeList();
  }
}
