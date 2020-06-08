import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit {
  accountType: string;
  balance: number;
  submitted = false;
  accountsForm: FormGroup;
  accountTypes = [
    { value: "Debit" },
    { value: "Credit" },
    { value: "Savings" },
  ]
  @Output() addAccount: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
    this.accountsForm = new FormGroup({
      'accountType': new FormControl(this.accountType, [
        Validators.required,
      ]),
      'balance': new FormControl(this.balance, [
        Validators.pattern("^\\d+(\\.\\d{1,2})?$")
      ]),
    });
  }

  onSubmit() {
    this.submitted = true;

    if (this.accountsForm.invalid) {
      return;
    }

    const account = {
      accountType: this.accountType.toUpperCase(),
      balance: this.balance ?? 0
    };
    this.addAccount.emit(account);
  }

  get f() { return this.accountsForm.controls; }
}
