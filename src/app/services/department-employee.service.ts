import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DepartmentEmployee } from '../models/department-employee.model';

@Injectable({
  providedIn: 'root'
})
export class DepartmentEmployeeService {
  private getAllUrl = 'http://localhost:8087/department-employee/all';
  
  
  constructor(private http: HttpClient) { }

  getList() {
    return this.http.get<DepartmentEmployee[]>(this.getAllUrl);
  }

}