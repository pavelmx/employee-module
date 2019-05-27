import { Employee } from "../models/employee.model";
import { PositionEmployee } from "../models/position-employee.model";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DepartmentEmployee } from '../models/department-employee.model';

@Injectable({
  providedIn: 'root'
})

export class EmployeeService {
  private getAllUrl = 'http://localhost:8087/employee/all';
  private getOneUrl = 'http://localhost:8087/employee/';
  private addEditUrl = 'http://localhost:8087/employee';
  private dismissUrl = 'http://localhost:8087/employee/dismissal/';
  private recoveryUrl = 'http://localhost:8087/employee/recovery/';
  private changePositionUrl = 'http://localhost:8087/position-employee/change';
  private changeDepartmentUrl = 'http://localhost:8087/department-employee/change';
  private posEmpUrl = 'http://localhost:8087/position-employee/current/';
  private depEmpUrl = 'http://localhost:8087/department-employee/current/';

  constructor(private http: HttpClient) { }

  getList() {
    return this.http.get<Employee[]>(this.getAllUrl);
  }

  getOne(id: any) {
    return this.http.get<Employee>(this.getOneUrl + id);
  }

  add(body: Employee, position_id: number, department_id: number){
    return this.http.post<Employee>(this.addEditUrl + "?position_id=" + position_id + "&department_id=" + department_id, body);
  }

  edit(body: Employee){
    return this.http.put<Employee>(this.addEditUrl, body);
  }

  dismiss(id: number){
    return this.http.get<string>(this.dismissUrl + id);
  }

  recovery(id: number){
    return this.http.get<string>(this.recoveryUrl + id);
  }

  changePosition(idEmployee: number, idNewPosition: number){
    return this.http.get<string>(this.changePositionUrl + "?employee_id=" + idEmployee + "&newposition_id=" + idNewPosition);
  }

  changeDepartment(idEmployee: number, idNewDepartment: number){
    return this.http.get<string>(this.changeDepartmentUrl + "?employee_id=" + idEmployee + "&newdepartment_id=" + idNewDepartment);
  }

  getCurrentPositionEmployee(idEmployee: number){
    return this.http.get<PositionEmployee>(this.posEmpUrl + idEmployee);
  }

  getCurrentDepartmentEmployee(idEmployee: number){
    return this.http.get<DepartmentEmployee>(this.depEmpUrl + idEmployee);
  }
}
