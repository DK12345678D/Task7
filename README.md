# Java JDBC – Employee Database App

## Objective
A simple console-based Java application to demonstrate CRUD operations using JDBC with MySQL.

## Tools Used
- Java (JDK 17+)
- MySQL
- JDBC Driver
- VS Code or IntelliJ IDEA

## Features
- Add new employee
- View all employees
- Update employee salary
- Delete employee by ID

## How to Run

1. **Set up MySQL:**
   ```sql
   CREATE DATABASE employeedb;
   USE employeedb;
   CREATE TABLE employees (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       salary DOUBLE
   );
