package minibank;

import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

/** Console capstone implementation for Manav Bhut (25IT011). */
public class MiniBank {
    private static final Path DATA = Path.of("minibank-data.txt");
    private final Map<String, Account> accounts = new TreeMap<>();
    private final Scanner input = new Scanner(System.in);
    public static void main(String[] args) { new MiniBank().run(); }
    private void run() { load(); if (accounts.isEmpty()) accounts.put("A1001", new SavingsAccount("A1001", "Demo Customer", money("1000"))); System.out.println("MiniBank — CEUE203 capstone"); while (menu()); save(); }
    private boolean menu() { System.out.print("\n1 Create  2 Deposit  3 Withdraw  4 Transfer  5 List  6 Interest  0 Exit\nChoice: "); String c=input.nextLine().trim(); try { switch(c) { case "1" -> create(); case "2" -> change(true); case "3" -> change(false); case "4" -> transfer(); case "5" -> accounts.values().forEach(System.out::println); case "6" -> accounts.values().forEach(Account::applyInterest); case "0" -> { return false; } default -> System.out.println("Choose a listed option."); } } catch (BankException | IllegalArgumentException e) { System.out.println("Transaction rejected: " + e.getMessage()); } return true; }
    private void create() { System.out.print("Account number: "); String id=input.nextLine().trim(); if(accounts.containsKey(id)||id.isBlank()) throw new IllegalArgumentException("Use a unique nonblank account number"); System.out.print("Customer name: ");String name=input.nextLine().trim(); System.out.print("Type (savings/current): ");String type=input.nextLine().trim(); Account a=type.equalsIgnoreCase("current")?new CurrentAccount(id,name,BigDecimal.ZERO):new SavingsAccount(id,name,BigDecimal.ZERO);accounts.put(id,a);System.out.println("Account created."); }
    private void change(boolean deposit) throws BankException { Account a=find(prompt("Account number: ")); BigDecimal amount=money(prompt("Amount: ")); if(deposit)a.deposit(amount);else a.withdraw(amount); System.out.println("Balance: "+a.balance); }
    private void transfer() throws BankException { Account from=find(prompt("From account: ")),to=find(prompt("To account: ")); BigDecimal amount=money(prompt("Amount: ")); transfer(from,to,amount);System.out.println("Transfer completed."); }
    private synchronized void transfer(Account from,Account to,BigDecimal amount)throws BankException{from.withdraw(amount);try{to.deposit(amount);}catch(BankException e){from.deposit(amount);throw e;}}
    private Account find(String id)throws AccountNotFoundException{Account a=accounts.get(id);if(a==null)throw new AccountNotFoundException(id);return a;}
    private String prompt(String p){System.out.print(p);return input.nextLine().trim();} private static BigDecimal money(String s){BigDecimal x=new BigDecimal(s);if(x.signum()<=0)throw new IllegalArgumentException("Amount must be positive");return x.setScale(2,RoundingMode.HALF_UP);}
    private void load(){if(!Files.exists(DATA))return;try{for(String line:Files.readAllLines(DATA)){String[] p=line.split("\\|",4);Account a=p[0].equals("S")?new SavingsAccount(p[1],p[2],new BigDecimal(p[3])):new CurrentAccount(p[1],p[2],new BigDecimal(p[3]));accounts.put(a.id,a);}}catch(IOException|RuntimeException e){System.out.println("Could not load saved data; starting clean.");accounts.clear();}}
    private void save(){List<String> lines=new ArrayList<>();for(Account a:accounts.values())lines.add((a instanceof SavingsAccount?"S":"C")+"|"+a.id+"|"+a.customer+"|"+a.balance);try{Files.write(DATA,lines);}catch(IOException e){System.out.println("Could not save: "+e.getMessage());}}
    static abstract class Account { final String id,customer; BigDecimal balance; Account(String i,String c,BigDecimal b){id=i;customer=c;balance=b.setScale(2);} synchronized void deposit(BigDecimal a)throws BankException{validate(a);balance=balance.add(a);} synchronized void withdraw(BigDecimal a)throws BankException{validate(a);if(!canWithdraw(a))throw new InsufficientFundsException(id);balance=balance.subtract(a);}void validate(BigDecimal a)throws BankException{if(a.signum()<=0)throw new InvalidAmountException();}boolean canWithdraw(BigDecimal a){return balance.compareTo(a)>=0;}void applyInterest(){}public String toString(){return "%s | %s | %s | Rs. %s".formatted(id,customer,getClass().getSimpleName(),balance);}}
    static final class SavingsAccount extends Account { SavingsAccount(String i,String c,BigDecimal b){super(i,c,b);}void applyInterest(){balance=balance.add(balance.multiply(new BigDecimal("0.03")).setScale(2,RoundingMode.HALF_UP));}}
    static final class CurrentAccount extends Account { CurrentAccount(String i,String c,BigDecimal b){super(i,c,b);}boolean canWithdraw(BigDecimal a){return balance.add(new BigDecimal("500")).compareTo(a)>=0;}}
    static class BankException extends Exception {BankException(String m){super(m);}} static class InvalidAmountException extends BankException{InvalidAmountException(){super("Amount must be positive");}} static class InsufficientFundsException extends BankException{InsufficientFundsException(String id){super("Insufficient funds in "+id);}} static class AccountNotFoundException extends BankException{AccountNotFoundException(String id){super("Account not found: "+id);}}
}
