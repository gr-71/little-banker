# little-banker

SpringBoot Application Example - RESTfull service 

servlet context-path: /api/v1

List of methods: 

POST        /customer/create            create a new customer
PUT         /customer/modify            update an existent customer
DELETE      /customer/remove/{id}       remove an existent customer by id
GET         /customer/{id}              find customer by id
GET         /customer                   find all customers

POST        /account/create            create a new account
PUT         /account/modify            update an existent account
DELETE      /account/remove/{iban}     remove an existent account by IBAN
GET         /account/summary/{iban}    get account summary by IBAN
GET         /account/balance/{iban}    get balance by IBAN 

POST        /transfer                   make a new money transfer
GET         /transfer/{amount}          find transfer by amount of money
GET         /transfer/{iban}            find transfer by IBAN
GET         /transfer/{message}         find transfer by message



