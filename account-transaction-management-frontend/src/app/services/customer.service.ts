import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Customer} from "../models/Customer";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  customerServiceUrl = 'http://localhost:8081/customers/';
  headerOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
      'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
    }),
  };

  constructor(private http: HttpClient) { }

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.customerServiceUrl, this.headerOptions);
  }

  deleteCustomer(customer: Customer){
    const url = `${this.customerServiceUrl}/${customer.id}`;
    return this.http.delete(url, this.headerOptions);
  }

  addCustomer(customer: Customer){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'access-control-allow-origin': '*',
      }),
    };
    return this.http.post<Customer>(this.customerServiceUrl, customer, httpOptions);
  }
}
