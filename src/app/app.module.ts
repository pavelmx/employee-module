import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeService } from './services/home.service';
import { CookieService } from 'ngx-cookie-service';
import { CarsComponent } from './cars/cars.component';
import { PrivateComponent } from './private/private.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    CarsComponent,
    PrivateComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [HomeService,CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
