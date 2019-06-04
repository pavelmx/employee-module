import { Injectable } from '@angular/core';

const FIRST_NAME = 'FIRST_NAME';
const LAST_NAME = 'LAST_NAME';
const ADRESS = 'ADRESS';
const SKYPE = 'SKYPE';
const ACTIVE = 'ACTIVE';
const EMAIL = 'EMAIL';
const PHONE_NUMBER = 'PHONE_NUMBER';
const DESCRIPTION = 'DESCRIPTION';
const POSITION = 'POSITION';
const DEPARTMENT = 'DEPARTMENT';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { 
    this.setActive('');
    this.setAdress('');
    this.setDepartment('');
    this.setDescription('');
    this.setSkype('');
    this.setEmail('');
    this.setFirstName('');
    this.setLastName('');
    this.setPhoneNumber('');
    this.setPosition('');   
   }

  clearFilter() {
    window.localStorage.clear();
  }

  public setFirstName(field: string) {
    window.sessionStorage.removeItem(FIRST_NAME);
    window.sessionStorage.setItem(FIRST_NAME, field);
  }

  public getFirstName(): string {
    return sessionStorage.getItem(FIRST_NAME);
  }

  public setLastName(field: string) {
    window.sessionStorage.removeItem(LAST_NAME);
    window.sessionStorage.setItem(LAST_NAME, field);
  }

  public getLastName(): string {
    return sessionStorage.getItem(LAST_NAME);
  }

  public setAdress(field: string) {
    window.sessionStorage.removeItem(ADRESS);
    window.sessionStorage.setItem(ADRESS, field);
  }

  public getAdress(): string {
    return sessionStorage.getItem(ADRESS);
  }

  public setSkype(field: string) {
    window.sessionStorage.removeItem(SKYPE);
    window.sessionStorage.setItem(SKYPE, field);
  }

  public getSkype(): string {
    return sessionStorage.getItem(SKYPE);
  }

  public setEmail(field: string) {
    window.sessionStorage.removeItem(EMAIL);
    window.sessionStorage.setItem(EMAIL, field);
  }

  public getEmail(): string {
    return sessionStorage.getItem(EMAIL);
  }

  public setActive(field: string) {
    window.sessionStorage.removeItem(ACTIVE);
    window.sessionStorage.setItem(ACTIVE, field);
  }

  public getActive(): string {
    return sessionStorage.getItem(ACTIVE);
  }

  public setPhoneNumber(field: string) {
    window.sessionStorage.removeItem(PHONE_NUMBER);
    window.sessionStorage.setItem(PHONE_NUMBER, field);
  }

  public getPhoneNumber(): string {
    return sessionStorage.getItem(PHONE_NUMBER);
  }

  public setPosition(field: string) {
    window.sessionStorage.removeItem(POSITION);
    window.sessionStorage.setItem(POSITION, field);
  }

  public getPosition(): string {
    return sessionStorage.getItem(POSITION);
  }

  public setDepartment(field: string) {
    window.sessionStorage.removeItem(DEPARTMENT);
    window.sessionStorage.setItem(DEPARTMENT, field);
  }

  public getDepartment(): string {
    return sessionStorage.getItem(DEPARTMENT);
  }

  public setDescription(field: string) {
    console.log(field)
    window.sessionStorage.removeItem(DESCRIPTION);
    window.sessionStorage.setItem(DESCRIPTION, field);
  }

  public getDescription(): string {
    return sessionStorage.getItem(DESCRIPTION);
  }
}
