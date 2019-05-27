import { Injectable } from '@angular/core';
import { Department } from '../models/department.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  private getAllUrl = 'http://localhost:8087/department/all';
  private getOneUrl = 'http://localhost:8087/department/';
  private addEditUrl = 'http://localhost:8087/department';
  
  constructor(private http: HttpClient) { }

  getList() {
    return this.http.get<Department[]>(this.getAllUrl);
  }

  getOne(id: number) {
    return this.http.get<Department>(this.getOneUrl + id);
  }

  add(body: Department, manager_id: any){   
    return this.http.post<Department>(this.addEditUrl + "?manager_id=" + manager_id, body);
  }

  edit(body: Department){
    return this.http.put<Department>(this.addEditUrl, body);
  }
}
