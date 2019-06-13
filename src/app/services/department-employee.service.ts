import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DepartmentEmployee } from '../models/department-employee.model';

@Injectable({
  providedIn: 'root'
})
export class DepartmentEmployeeService {
  private getAllUrl = 'http://localhost:8087/department-employee/all';
  private getAllPageUrl = 'http://localhost:8087/department-employee/all-page';
  private getBetweenDateUrl = 'http://localhost:8087/department-employee/all-by-date';
  
  constructor(private http: HttpClient) { }
  
  getBetweenDate(employeeId: number, start: Date, end: Date){
    return this.http.get<DepartmentEmployee[]>(`${this.getBetweenDateUrl}?employeeId=${employeeId}&start=${start}&end=${end}`);
  }

  getPageableList(page: number, size: number, column: string, order: string) {
    return this.http.get<DepartmentEmployee[]>(this.getAllPageUrl + "?size=" + size + "&page=" 
    + page + "&column=" + column + "&order=" + order);
  }

}