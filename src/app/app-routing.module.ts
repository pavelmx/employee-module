import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PrivateComponent } from './private/private.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  
  {
    path: 'private',
    component: PrivateComponent
  },
  {
    path: 'home-server',
    component: HomeComponent
  },
  {
    path: 'home',    
    redirectTo: 'home-server',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
