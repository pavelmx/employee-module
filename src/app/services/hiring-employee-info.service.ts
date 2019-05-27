import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HiringEmployeeInfo } from '../models/hiring-employee-info.model';

@Injectable({
  providedIn: 'root'
})
export class HiringEmployeeInfoService {
  private getAllUrl = 'http://localhost:8087/hiring-employee-info/all';
  
  constructor(private http: HttpClient) { }

  getList() {
    return this.http.get<HiringEmployeeInfo[]>(this.getAllUrl);
  }

    
}
