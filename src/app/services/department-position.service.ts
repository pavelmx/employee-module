import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DepartmentPosition } from '../models/department-position.model';

@Injectable({
  providedIn: 'root'
})
export class DepartmentPositionService {

  private getAllByEmplId = 'http://localhost:8087/department-position/';
  
  constructor(private http: HttpClient) { }
  
  getByEmplId(employeeId: number){
    return this.http.get<DepartmentPosition[]>(`${this.getAllByEmplId}/${employeeId}`);
  }

}