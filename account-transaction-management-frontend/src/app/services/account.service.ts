import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Account} from "../models/Account";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  formatUrl = (customerId) => `http://localhost:8082/customer/${customerId}/accounts`;

  headerOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
      'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
    }),
  };

  constructor(private http: HttpClient) { }

  getAccounts(customerId: number): Observable<Account[]> {
    return this.http.get<Account[]>(this.formatUrl(customerId), this.headerOptions);
  }

  deleteAccount(customerId: number ,accountId: number){
    const url = `${this.formatUrl(customerId)}/${accountId}`;
    return this.http.delete(url, this.headerOptions);
  }

  addAccount(customerId: number, account: Account){
    console.log(`account balance is ${account.balance}`);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'access-control-allow-origin': '*',
      }),
    };
    return this.http.post<Account>(this.formatUrl(customerId), account, httpOptions);
  }
}
