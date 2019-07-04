import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { DepartmentListComponent } from './department-list/department-list.component';
import { PositionListComponent } from './position-list/position-list.component';
import { HiringInfoListComponent } from './hiring-info-list/hiring-info-list.component';
import { DepartmentEmployeeListComponent } from './department-employee-list/department-employee-list.component';
import { PositionEmployeeListComponent } from './position-employee-list/position-employee-list.component';
import { HomeComponent } from './home/home.component';




const routes: Routes = [
  {
    path: 'position-list',    
    component: PositionListComponent
  },
  {
    path: 'position-employee-list',    
    component: PositionEmployeeListComponent
  },
  {
    path: 'department-employee-list',    
    component: DepartmentEmployeeListComponent
  },
  {
    path: 'employee-list',    
    component: EmployeeListComponent
  },
  {
    path: 'hiring-info-list',    
    component: HiringInfoListComponent
  },
  {
    path: 'department-list',    
    component: DepartmentListComponent
  },
  {
    path: 'home',    
    component: HomeComponent
  },
  {
    path: '',    
    redirectTo: 'employee-list',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
