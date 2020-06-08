import { Component, OnInit } from '@angular/core';
import {Customer} from "../../models/Customer";
import {CustomerService} from "../../services/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers: Customer[];

  constructor(private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe(customers => {
      this.customers = customers;
    });
  }

  deleteCustomer(customer: Customer) {
    if(confirm("Are you sure you want to permanently delete this customer?")) {
      this.customers = this.customers.filter(c => c.id !== customer.id);
      this.customerService.deleteCustomer(customer).subscribe(() => console.log('customer deleted'));
    }
  }

  saveCustomer(customer: Customer) {
    this.customerService.addCustomer(customer).subscribe(
      () => this.ngOnInit()
    );
  }

  goToAccounts(customer: Customer) {
    const accountsUrl = `/accounts/${customer.id}`;
    const customerInfo = `${customer.firstName} ${customer.lastName} (BSN: ${customer.socialSecurityNumber})`;
    console.log(customerInfo);
    this.router.navigate([accountsUrl],{ state: { customer: customerInfo }});
  }
}
