import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Position } from '../models/position.model';
import { PositionEmployee } from '../models/position-employee.model';


@Injectable({
  providedIn: 'root'
})
export class PositionEmployeeService {
  private getAllUrl = 'http://localhost:8087/position-employee/all';
  private getAllPageUrl = 'http://localhost:8087/position-employee/all-page';

  constructor(private http: HttpClient) { }

  getList() {
    return this.http.get<PositionEmployee[]>(this.getAllUrl);
  }

  getPageableList(page: number, size: number, column: string, order: string) {
    return this.http.get<PositionEmployee[]>(this.getAllPageUrl + "?size=" + size + "&page=" 
    + page + "&column=" + column + "&order=" + order);
  }

}
