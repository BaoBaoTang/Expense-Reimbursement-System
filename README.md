# Expense-Reimbursement-System

## Description
The Expense Reimbursement App will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies
* Apache Tomcat 9.0
* Bootstrap 4
* Servlet 4.0.1
* Jackson Databind 2.12.3
* Bcrypt 0.9.0
* Junit 5
* Log4j 1.2.17
* JavaMail 1.6.2
* PostgreSQL 42.2.20
* Mockito 2.22.0
* Sonar Cloud

## Features
* Register with email, one-time password will be generated and sent through email
* Password is encrypted in the database
* Upload an image as receipt when creating reimbursement, image will be persistent in the database
* Use log4j to log the errors/exceptions
* Use mockito to implement the TDD, test coverage >60%

## Getting Started
### Prerequisite
* Tomcat 9.0
* JDK 8 or above
* Apache Maven 3.8

### Usage
* Git clone the repository
* Run `maven package` command
* Deploy the war package using tomcat

