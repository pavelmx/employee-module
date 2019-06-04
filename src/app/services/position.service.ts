import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Position } from '../models/position.model';


@Injectable({
  providedIn: 'root'
})
export class PositionService {
  private getAllUrl = 'http://localhost:8087/position/all';
  private getAllEnableUrl = 'http://localhost:8087/position/enable/all';
  private getOneUrl = 'http://localhost:8087/position/';
  private addEditUrl = 'http://localhost:8087/position';
  private disableUrl = 'http://localhost:8087/position/disable/';
  private enableUrl = 'http://localhost:8087/position/enable/';
  private getAllPageUrl = 'http://localhost:8087/position/all-page';

  constructor(private http: HttpClient) { }

  getList() {
    return this.http.get<Position[]>(this.getAllUrl);
  }

  getPageableList(page: number, size: number, column: string, order: string) {
    return this.http.get<Position[]>(this.getAllPageUrl + "?size=" + size + "&page=" 
    + page + "&column=" + column + "&order=" + order);
  }

  getEnableList() {
    return this.http.get<Position[]>(this.getAllEnableUrl);
  }

  getOne(id: number) {
    return this.http.get<Position>(this.getOneUrl + id);
  }

  add(body: Position){
    return this.http.post<Position>(this.addEditUrl, body);
  }

  edit(body: Position){
    return this.http.put<Position>(this.addEditUrl, body);
  }

  disable(id: number){
    console.log(id)
    return this.http.get<any>(this.disableUrl + id);
  }

  enable(id: number){
    return this.http.get<any>(this.enableUrl + id);
  }
}
