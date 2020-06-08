import { Component, OnInit } from '@angular/core';
import {Account} from "../../models/Account";
import {AccountService} from "../../services/account.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TransactionService} from "../../services/transaction.service";
import {Transaction} from "../../models/Transaction";

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  transactions: Transaction[]
  id: number;

  constructor(private transactionService: TransactionService, private router: Router, private route: ActivatedRoute) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.transactionService.getTransactions(this.id).subscribe(transactions => {
      this.transactions = transactions;
    });
    console.log(this.transactions);
  }

}
