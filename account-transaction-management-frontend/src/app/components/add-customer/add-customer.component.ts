import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  customerForm: FormGroup;
  firstName: string;
  lastName: string;
  socialSecurityNumber: number;
  @Output() addCustomer: EventEmitter<any> = new EventEmitter();
  submitted = false;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.customerForm = new FormGroup({
      'firstName': new FormControl(this.firstName, [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(35),
        Validators.pattern("[a-zA-Z ]*")
      ]),
      'lastName': new FormControl(this.lastName, [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(35),
        Validators.pattern("[a-zA-Z ]*")
      ]),
      'socialSecurityNumber': new FormControl(this.socialSecurityNumber, [
        Validators.required,
        Validators.pattern("^[0-9]{9}$")
      ]),
    });
  }

  get f() { return this.customerForm.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.customerForm.invalid) {
      return;
    }

    const customer = {
      firstName: this.firstName,
      lastName: this.lastName,
      socialSecurityNumber: this.socialSecurityNumber
    };
    this.addCustomer.emit(customer);
  }
}
