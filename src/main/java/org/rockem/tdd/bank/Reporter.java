package org.rockem.tdd.bank;

import java.util.List;

public class Reporter {

    private static final String REPORT_HEADER = "DATE          | AMOUNT  | BALANCE";
    private Integer subtotal = 0;
    private StringBuilder report = new StringBuilder(REPORT_HEADER + "\n");

    public String printReport(List<Transaction> transactions){
        transactions.forEach(t -> logTransaction(t.getDate(), t.getAmount()));
        return report.toString();
    }

    private void logTransaction(String date, Integer transaction) {
        report.append(String.format("%s | %.2f               | %.2f", date, (double) transaction , (double) (subtotal += transaction)));
        report.append("\n");
    }

}
