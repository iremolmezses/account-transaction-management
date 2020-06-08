import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomersComponent} from "./components/customers/customers.component";
import {AccountsComponent} from "./components/accounts/accounts.component";
import {TransactionsComponent} from "./components/transactions/transactions.component";

const routes: Routes = [
  {
    path: '',
    component:CustomersComponent
  },
  {
    path: 'accounts/:id',
    component:AccountsComponent
  },
  {
    path: 'transactions/:id',
    component:TransactionsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
