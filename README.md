# Banking System

A console-based Banking System built with Core Java, 
demonstrating Object-Oriented Programming principles.

## Features
- Create bank account with unique Account ID
- Secure login with password authentication
- Deposit and withdraw funds
- Transfer money between accounts
- View complete transaction history with timestamps

## Tech Stack
- Java 24
- Core Java (OOP, Collections, Exception Handling)

## Concepts Used
- Encapsulation — private fields with getters
- Custom Exception Handling — InsufficientFundsException, AccountNotFoundException
- Java Collections — HashMap for accounts, ArrayList for transactions
- UUID for unique account ID generation

## How to Run
cd src
javac *.java
java Main

## Project Structure
src/
├── Main.java
├── Bank.java
├── Account.java
├── Transaction.java
├── AccountNotFoundException.java
└── InsufficientFundsException.java
