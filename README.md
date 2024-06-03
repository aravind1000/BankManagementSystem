# RealBank

## Overview
RealBank is a simple console-based banking application written in Java. It allows users to create accounts, deposit and withdraw money, check their balance, and view transaction history. The application also provides an option to close an account.

## Features
1. **Account Creation**: Create a new account with a unique account number.
2. **Savings Account Management**: 
    - Deposit money.
    - Withdraw money.
    - Check balance.
    - View transaction history.
3. **Account Closure**: Close an existing account.
4. **Exit**: Exit the application.

## Usage

### Main Menu
Upon running the application, you will be presented with the main menu:


### 1. Account Creation
To create a new account:
1. Select option `1`.
2. Enter your name and phone number when prompted.
3. The application will create an account and provide you with a unique account number.

### 2. Savings Account
To manage your savings account:
1. Select option `2`.
2. Enter your account index to log in.
3. You will be presented with the Savings Account menu:

    ```
    1. Deposit
    2. Withdrawal
    3. Transaction History
    4. Balance Inquiry
    5. Exit
    ```

#### Deposit
1. Select option `1`.
2. Enter the amount to deposit.
3. The amount will be credited to your account, and the current balance will be displayed.

#### Withdrawal
1. Select option `2`.
2. Enter the amount to withdraw.
3. If sufficient balance is available, the amount will be debited from your account, and the current balance will be displayed. Otherwise, an "Insufficient Balance" message will be shown.

#### Transaction History
1. Select option `3`.
2. View the list of all transactions (deposits and withdrawals) made in your account.

#### Balance Inquiry
1. Select option `4`.
2. View your current account balance.

#### Exit Savings Account
1. Select option `5` to exit the Savings Account menu and return to the main menu.

### 3. Account Closure
To close an existing account:
1. Select option `3`.
2. Enter your name when prompted.
3. If an account with the entered name exists, it will be closed and removed from the system. Otherwise, an error message will be displayed.

### 4. Exit
Select option `4` to exit the application.

## Code Structure
- **Account Class**: Stores account details (name and phone number).
- **SavingsAccount Class**: Provides methods for deposit, withdrawal, and balance inquiry.
- **Activity Class**: Records transaction details (status, transaction amount, remaining balance).
- **RealBank Class**: Main class that manages the overall application flow.

## Running the Application
To run the application, compile the Java files and execute the `RealBank` class. Ensure you have Java installed on your system.

```sh
javac RealBank.java
java RealBank
