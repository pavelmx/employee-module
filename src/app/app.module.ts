import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeService } from './services/employee.service';
import { CookieService } from 'ngx-cookie-service';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatTableModule} from '@angular/material';
import { DepartmentListComponent } from './department-list/department-list.component';
import { DepartmentService } from './services/department.service';
import { PositionListComponent } from './position-list/position-list.component';
import { HiringInfoListComponent } from './hiring-info-list/hiring-info-list.component';
import { DepartmentEmployeeListComponent } from './department-employee-list/department-employee-list.component';


@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    DepartmentListComponent,
    PositionListComponent,
    HiringInfoListComponent,
    DepartmentEmployeeListComponent,
  ],
  imports: [
    
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule, MatCheckboxModule, MatTableModule
  ],
  providers: [
    EmployeeService,
    CookieService,
    DepartmentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
