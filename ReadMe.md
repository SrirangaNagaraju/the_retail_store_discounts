##The Retails Store Discounts Project

#Instructions

This is mini project for retails store which uses discounts as motivator for buying goods.

#Technology used:

* Java 1.8
* SpringBoot
* Maven
* Junit using Mockito and Junit5.
* intellij/Eclipse/STS to run and test the project.

Since this is using a spring boot project you can directly run the TheRetailStoreDiscountsApplication.java class or using postman aslo(both implemention in this application).

Run mvn clean test from the root folder, the JaCoCo code coverage report will be generated at target/site/jacoco/*
Open the target/site/jacoco/index.html file, review the code coverage report.

postman URL: GET- http://localhost:8080/restore/amount

Headers - Content-Type - application/json
Accept - */*

Body-
 choose row

click on Send Request to Server,

we will get below Json response get it.



==========================
CURL  Request 
----------
curl --location --request GET 'http://localhost:8080/restore/amount' \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--data-raw ''

Response
----------
GET http://localhost:8080/restore/amount: {
  "Network": {
    "addresses": {
      "local": {
        "address": "::1",
        "family": "IPv6",
        "port": 51968
      },
      "remote": {
        "address": "::1",
        "family": "IPv6",
        "port": 8080
      }
    }
  },
  "Request Headers": {
    "content-type": "application/json",
    "accept": "*/*",
    "user-agent": "PostmanRuntime/7.29.0",
    "postman-token": "f6381959-7f00-4a9c-a85d-7c353760c0a4",
    "host": "localhost:8080",
    "accept-encoding": "gzip, deflate, br",
    "connection": "keep-alive"
  },
  "Request Body": "",
  "Response Headers": {
    "content-type": "text/plain;charset=UTF-8",
    "content-length": "31",
    "date": "Thu, 28 Apr 2022 15:32:34 GMT",
    "keep-alive": "timeout=60",
    "connection": "keep-alive"
  },
  "Response Body": "Total Amount To Be Paid: 4620.0"
}

## General Information 

- Products can be added to the shopping cart with the quantity.
- After adding all the products, the application will find out the total amount for the products added to the cart.
- Discount will be calculated for Employee or affiliate or general customer who has more then 2 years with the store.
- Also Discount will be given to non grocery products.

