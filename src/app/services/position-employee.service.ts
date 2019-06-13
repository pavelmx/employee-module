import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Position } from '../models/position.model';
import { PositionEmployee } from '../models/position-employee.model';


@Injectable({
  providedIn: 'root'
})
export class PositionEmployeeService {
  private getAllPageUrl = 'http://localhost:8087/position-employee/all-page';
  private getAllByEmployeeIdUrl = 'http://localhost:8087/position-employee/all/';

  constructor(private http: HttpClient) { }

  getPageableList(page: number, size: number, column: string, order: string) {
    return this.http.get<PositionEmployee[]>(this.getAllPageUrl + "?size=" + size + "&page=" 
    + page + "&column=" + column + "&order=" + order);
  }

  getAllByEmployeeId(id: number){
    return this.http.get<PositionEmployee[]>(`${this.getAllByEmployeeIdUrl}/${id}`);
  }

}
