import { Component, OnInit, ViewChildren, QueryList, ViewChild, ElementRef } from '@angular/core';
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
import { ToastService } from '../util/toast.service';
import { EmployeeFilter } from '../models/employee-filter.model';
import { StorageService } from '../services/storage.service';

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
  order: string = 'asc';
  length: number;
  lastPage: number;
  currentEmployee: Employee;
  active: boolean = true;
  filter: EmployeeFilter = new EmployeeFilter();

  @ViewChild('close_add') closeAddModal: ElementRef;
  @ViewChild('close_info') closeEditModal: ElementRef;
  @ViewChild('close_pos') closePosModal: ElementRef;
  @ViewChild('close_dep') closeDepModal: ElementRef;
  @ViewChild('close_rec') closeRecModal: ElementRef;
  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;


  constructor(
    private employeeService: EmployeeService,
    private positionService: PositionService,
    private departmentService: DepartmentService,
    private toast: ToastService,
    private storage: StorageService) { }

  ngOnInit() {
    this.initFilter();
    this.initDepartmentList();
    this.initEmployeeList();
    this.initPositionList();
  }

  initFilter(){
    console.log("init filter")
    this.filter.firstName = this.storage.getFirstName();
    this.filter.lastName = this.storage.getLastName();
    this.filter.adress = this.storage.getAdress();
    this.filter.phoneNumber = this.storage.getPhoneNumber();
    this.filter.skype = this.storage.getSkype();
    this.filter.email = this.storage.getEmail();
    this.filter.description = this.storage.getDescription();
    this.filter.active = this.storage.getActive();
    console.log(this.filter)    
    //position
    //department
  }

  resetFilter(){
    this.storage.clearFilter();
    this.initFilter();
  }

  saveFilter(){
    console.log("save filter")
    console.log(this.filter.description)
    this.storage.setFirstName(this.filter.firstName);
    this.storage.setLastName(this.filter.lastName);
    this.storage.setAdress(this.filter.adress);
    this.storage.setPhoneNumber(this.filter.phoneNumber);
    this.storage.setSkype(this.filter.skype);
    this.storage.setEmail(this.filter.email);
    this.storage.setDescription(this.filter.description);
    this.storage.setActive(this.filter.active);
    //position
    //department
    this.initEmployeeList();
  }

  initEmployeeList() {   
    this.employeeService.getPageableList(this.page, this.size, this.column, this.order, this.filter)
      .subscribe(
        response => {
          this.list = response['content']; 
        this.length = response['totalElements'];
        this.lastPage = response['totalPages']; 
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
          this.closeAddModal.nativeElement.click();
          form.resetForm();
          this.toast.showSuccess("Success","New employee '" + response.personalInfo.firstName + " " +
          response.personalInfo.lastName + "' added");
        },
        error => {
          console.log(error)
          this.toast.showError("Error", error.error.message);
        }
      );
  }

  setEmployee(employee: Employee){
    this.currentEmployee = employee;
  }

  setEditEmployee(employee: Employee) {
    this.active = employee.active;
    this.employeeService.getOne(employee.id)
      .subscribe(
        response => {
          this.formedit = response;
          this.personalInfo = this.formedit.personalInfo;
          
        }
      );
  }

  edit(form: NgForm) {
    console.log(this.formedit)
    this.employeeService.edit(this.formedit)
      .subscribe(
        response => {
          console.log(response)
          this.initEmployeeList();
          this.closeEditModal.nativeElement.click();
          this.toast.showSuccess("Success","Employee '" + response.personalInfo.firstName + " " +
          response.personalInfo.lastName + "' updated");
        },
        error => {
          console.log(error)
          this.toast.showError("Error", error.error.message);
        }
      );
  }

  dismissEmployee(id: number) {
    
      this.employeeService.dismiss(id)
        .subscribe(
          response => {
            this.initEmployeeList();
            this.toast.showSuccess("Success", response.message);
            console.log(response)
          },
          error => {            
            this.toast.showError("Error", error.error.message);
            console.log(error)
          }
        );       
  }

  recoveryEmployee() {
    
    this.employeeService.recovery(this.currentEmployee.id, this.formchange.rec_position_id, 
       this.formchange.rec_department_id)
      .subscribe(
        response => {
          this.initEmployeeList();
          this.toast.showSuccess("Success", response.message);
          this.closeRecModal.nativeElement.click();
        },
        error => {          
          this.toast.showError("Error", error.error.message);
          console.log(error)
        }
      );       
}

  setCurrentPosition(id: number) {
    if(this.active){
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
      }else{
        this.formchange.currentPositionName = '';
      }
  }

  changePosition() {
    this.employeeService.changePosition( this.formchange.currentEmployeeId, this.formchange.position_id)
      .subscribe(
        response => {
          this.setCurrentPosition(this.formchange.currentEmployeeId);
          this.toast.showSuccess("Success", response.message);
          this.closePosModal.nativeElement.click();
        },
        error => {         
          this.toast.showError("Error", error.error.message);
          console.log(error)
        }
      );
  }

  setCurrentDepartment(id: number) {
    console.log(this.active)
    if(this.active){
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
      }else{
        this.formchange.currentDepartmentName = '';
      }
  }

  changeDepartment() {   
    this.employeeService.changeDepartment(this.formchange.currentEmployeeId, this.formchange.department_id)
      .subscribe(
        response => {
          this.setCurrentDepartment(this.formchange.currentEmployeeId);
          this.toast.showSuccess("Success", response.message);
          this.closeDepModal.nativeElement.click();
        },
        error => {
          this.toast.showError("Error", error.error.message);
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
    
    this.initEmployeeList();
  }
}
