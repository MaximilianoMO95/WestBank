package westbank.negocio;

public class Account {
        public int accountNumber;
        protected int balance;
        public String description;

        public Account(int accountNumber) {
                this.accountNumber = accountNumber;
                this.balance = 0;
        }

        public Account(int accountNumber, int balance, String description) {
                this.description = description;
                this.accountNumber = accountNumber;
                this.balance = balance;
        }

        public int getAccountNumber() {
                return accountNumber;
        }

        public int checkBalance() {
                return balance;
        }

        public String getDescription() {
                return description;
        }

        public void withdraw(int amount) {
                balance -= amount;
        }

        public void deposit(int amount) {
                balance += amount;
        }

        public void moneyTransfer(int amount, Account destinationAccount) {
                balance -= amount;
                destinationAccount.deposit(amount);
        }
}

