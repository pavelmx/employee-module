
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'
})

export class HomeService {
  private resUrl = 'http://localhost:8081/client';
  private token = this.token = this.cookieService.get('access_token');

  constructor(private http: HttpClient,
    private cookieService: CookieService) { }

  getList(): Observable<string[]> {
    return this.http.get<string[]>(this.resUrl + "/cars?access_token=702a785b-e72a-4255-822c-d78ecc8ee977" );
  }
}
