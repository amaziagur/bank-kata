package org.rockem.tdd.bank;

import java.util.LinkedList;
import java.util.List;

public class Account {

    private List<Transaction> transactions = new LinkedList<>();

    private ClockProvider clock = new Clock();

    public Account(ClockProvider clock){
        this.clock = clock;
    }

    public void deposit(int amount) {
        transact(amount);
    }

    public void withdraw(int amount) {
        transact(-amount);
    }

    private void transact(int amount) {
        transactions.add(new Transaction(clock.getToday(), amount));
    }

    public void printStatement() {
        System.out.print(new Reporter().printReport(transactions));
    }
}
