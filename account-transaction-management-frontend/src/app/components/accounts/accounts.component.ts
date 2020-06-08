import { Component, OnInit } from '@angular/core';
import {AccountService} from "../../services/account.service";
import {ActivatedRoute, NavigationStart, Router} from "@angular/router";
import {Account} from "../../models/Account";
import {filter} from "rxjs/operators";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  accounts: Account[]
  id: number;
  customerInfo: string;

  constructor(private accountService: AccountService, private router: Router, private route: ActivatedRoute) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;

    if (this.router.getCurrentNavigation().extras.state) {
      this.customerInfo = this.router.getCurrentNavigation().extras.state.customer;
    }

    this.router.events.pipe(
      filter(e =>e instanceof NavigationStart),
      filter((e: NavigationStart)=>e.navigationTrigger == "popstate")
    )
      .subscribe((x: NavigationStart) => {
        if ( x.restoredState ) {
          this.router.getCurrentNavigation().extras.state={...x.restoredState, navigationId:x.id } ;
          console.warn('restoring navigation id:', x.restoredState.navigationId);
        }
      });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.accountService.getAccounts(this.id).subscribe(accounts => {
      this.accounts = accounts;
    });
    console.log(this.accounts);
  }

  deleteAccount(account: Account) {
    if(confirm("Are you sure you want to permanently delete this account?")) {
      this.accounts = this.accounts.filter(a => a.id !== account.id);
      this.accountService.deleteAccount(this.id, account.id).subscribe(() => console.log('account deleted'));
    }
  }

  saveAccount(account: Account) {
    this.accountService.addAccount(this.id, account).subscribe(
      () => this.ngOnInit()
    );
  }

  goToTransactions(id: number) {
    const transactionsUrl = `/transactions/${id}`;
    this.router.navigate([transactionsUrl]);
  }
}
