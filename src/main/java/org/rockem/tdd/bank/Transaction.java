package org.rockem.tdd.bank;

public class Transaction {

    private String date;
    private Integer amount;

    public Transaction(String date, Integer amount) {
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public Integer getAmount() {
        return amount;
    }
}
