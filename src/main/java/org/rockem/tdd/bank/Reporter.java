package org.rockem.tdd.bank;

import java.util.List;

public class Reporter {

    private static final String HEADER_REPORT = "DATE          | AMOUNT  | BALANCE";
    private Integer subtotal = 0;

    public String printReport(List<Transaction> transactions){
        StringBuilder report = createReport();
        transactions.forEach(t -> logTransaction(report, t.getDate(), t.getAmount()));
        return report.toString();
    }

    private void logTransaction(StringBuilder body, String date, Integer transaction) {
        body.append(String.format("%s | %.2f               | %.2f", date, (double) transaction , (double) (subtotal += transaction)));
        body.append("\n");
    }

    private StringBuilder createReport() {
        return new StringBuilder(HEADER_REPORT + "\n");
    }
}
