
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})

export class HomeService {
  private resUrl = 'http://localhost:8081/client';

  constructor(private http: HttpClient) { }

  getList(token: string): Observable<string[]> {
    return this.http.get<string[]>(this.resUrl + "/carsfree?access_token=" + token);
  }
}
