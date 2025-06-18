package org.example.bankingprog;



import com.mongodb.client.*;
        import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.Scanner;

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message) {
        super(message);
    }
}

class BankAccount {
    private final String accountNumber;
    private String name;
    private double balance;

    public BankAccount(String accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) throws InvalidTransactionException {
        if (amount <= 0) throw new InvalidTransactionException("Amount must be positive.");
        balance += amount;
    }

    public void withdraw(double amount) throws InvalidTransactionException {
        if (amount <= 0) throw new InvalidTransactionException("Amount must be positive.");
        if (amount > balance) throw new InvalidTransactionException("Insufficient funds.");
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public Document toDocument() {
        return new Document("accountNumber", accountNumber)
                .append("name", name)
                .append("balance", balance);
    }

    public static BankAccount fromDocument(Document doc) {
        BankAccount account = new BankAccount(doc.getString("accountNumber"), doc.getString("name"));
        account.balance = doc.getDouble("balance");
        return account;
    }
}

public class BankingSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    private static final MongoDatabase database = mongoClient.getDatabase("bankDB");
    private static final MongoCollection<Document> collection = database.getCollection("accounts");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 :createAccount();
                    case 2 : deposit();
                    case 3 : withdraw();
                    case 4 :checkBalance();
                    case 5 :{
                        System.out.println("Thank you for using the banking system.");
                        mongoClient.close();
                        return;
                    }
                    default : System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accNo = scanner.nextLine();
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();

        if (collection.find(Filters.eq("accountNumber", accNo)).first() != null) {
            System.out.println("Account already exists.");
            return;
        }

        BankAccount account = new BankAccount(accNo, name);
        collection.insertOne(account.toDocument());
        System.out.println("Account created successfully.");
    }

    private static void deposit() throws Exception {
        BankAccount account = fetchAccount();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        account.deposit(amount);
        updateAccount(account);
        System.out.println("Deposit successful. New Balance: " + account.getBalance());
    }

    private static void withdraw() throws Exception {
        BankAccount account = fetchAccount();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        account.withdraw(amount);
        updateAccount(account);
        System.out.println("Withdrawal successful. New Balance: " + account.getBalance());
    }

    private static void checkBalance() throws Exception {
        BankAccount account = fetchAccount();
        System.out.println("Current Balance: " + account.getBalance());
    }

    private static BankAccount fetchAccount() throws AccountNotFoundException {
        System.out.print("Enter account number: ");
        String accNo = scanner.nextLine();

        Document doc = collection.find(Filters.eq("accountNumber", accNo)).first();
        if (doc == null) throw new AccountNotFoundException("Account not found.");
        return BankAccount.fromDocument(doc);
    }

    private static void updateAccount(BankAccount account) {
        collection.replaceOne(Filters.eq("accountNumber", account.getAccountNumber()), account.toDocument());
    }
}

