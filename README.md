# ACCOUNT TRANSACTION MANAGEMENT

## Requirements
(Open) JDK 8

Node.js & npm (https://nodejs.org/)

Maven 3 (https://maven.apache.org/)

An IDE (VS Code or IntelliJ)

## System composition
### account-transaction-management-frontend
The Angular frontend.

### customer-service
A module that provides customer related REST services to the frontend.

### account-service
A module that provides account related REST services to the frontend.

### transaction-service
A module that provides transaction related REST services to the frontend.

### account-transaction-management-api
The backend module that provides data access to the web services.

## Run the application

sh run.sh

The frontend should go live at http://localhost:4200/

## Nice-to-have for later (not implemented due to limited time)
API access protection via api keys

Dockerization

Professional UI with UX engineer (currently designed by a backend engineer)

More tests

## Screenshots

Note: Customer and account table rows are clickable and navigates to other pages (i.e if you click on a customer, it navigates to the page that displays the account of the customer)

### Index page with customers from in-memory db
![index page with customers from in-memory db](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/all%20customers.png)

### Before adding a new customer
![before adding a new customer](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/before%20add%20customer.png)

### After adding a new customer
![after adding a new customer](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/after%20add%20customer.png)

### Accounts of a customer
![accounts of a customer](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/accounts%20of%20a%20customer.png)

### Transactions of an account
![transactions of an account](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/transactions%20of%20an%20account.png)

### Before add account (with zero initial balance)
![before add account](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/before%20add%20account%20-%20with%20zero%20initial%20balance.png)

### After add account (with zero initial balance)
![after add account](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/after%20add%20account.png)

### Transactions of just created account (with zero initial balance)
![transactions of just created account](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/transactions%20of%20new%20created%20account.png)

### Before add account (with initial balance)
![before add account](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/add%20account%20with%20initial%20balance.png)

### After add account (with initial balance)
![after add account](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/after%20add%20account%202.png)

### Transactions of just created account (with initial balance)
![transactions of just created account](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/transactions%20of%20new%20created%20account%20with%20balance.png)

### Before deleting an account
![before deleting an account](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/before%20delete%20account.png)

### After deleting an account
![after deleting the account](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/after%20delete%20account.png)

### Before deleting a customer
![before deleting a customer](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/before%20delete%20customer.png)

### After deleting the customer
![after deleting the customer](https://github.com/iremolmezses/account-transaction-management/blob/master/screenshots/after%20delete%20customer.png)
