import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Transaction} from "../models/Transaction";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  transactionServiceUrl = 'http://localhost:8083/transactions';
  headerOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
      'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
    }),
  };

  constructor(private http: HttpClient) { }

  getTransactions(accountId: number): Observable<Transaction[]> {
    // console.log(`getting meals for flight ${flightNumber} ${flightDate}`);
    const url = `${this.transactionServiceUrl}/${accountId}`;
    return this.http.get<Transaction[]>(url, this.headerOptions);
  }
}
