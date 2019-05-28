import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HiringEmployeeInfo } from '../models/hiring-employee-info.model';

@Injectable({
  providedIn: 'root'
})
export class HiringEmployeeInfoService {
  private getAllUrl = 'http://localhost:8087/hiring-employee-info/all';
  private getAllPageUrl = 'http://localhost:8087/hiring-employee-info/all-page';

  constructor(private http: HttpClient) { }

  getList() {
    return this.http.get<HiringEmployeeInfo[]>(this.getAllUrl);
  }

  getPageableList(page: number, size: number, column: string, order: string) {
    return this.http.get<HiringEmployeeInfo[]>(this.getAllPageUrl + "?size=" + size + "&page=" 
    + page + "&column=" + column + "&order=" + order);
  }
}
