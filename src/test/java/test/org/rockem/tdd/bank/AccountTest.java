package test.org.rockem.tdd.bank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rockem.tdd.bank.Account;
import org.rockem.tdd.bank.Clock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountTest {

    private static final String PRINT_OUT_HEADER = "DATE          | AMOUNT  | BALANCE" + "\n" ;
    private final static String DATE = "01/01/2017";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Clock clock = mock(Clock.class);
    private final Account account = new Account(clock);


    @Before
    public void init() {
        when(clock.getToday()).thenReturn(DATE);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanup() {
        System.setOut(null);
    }

    @Test
    public void printZeroBalance() throws Exception {
        callReport();
        assertTransaction("");
    }

    private void assertTransaction(String actual) {
        assertThat(PRINT_OUT_HEADER + actual, is(outContent.toString()));
    }

    private void callReport() {
        account.printStatement();
    }

    @Test
    public void deposit100(){
        transact().times(1).deposit(100);
        callReport();
        assertTransaction(DATE + " | 100.00               | 100.00"
                                                   + "\n");
    }

    @Test
    public void depositMoreThanOnce(){
        transact().times(2).deposit(100);
        callReport();
        assertTransaction(DATE + " | 100.00               | 100.00" + "\n"+
                                        DATE + " | 100.00               | 200.00" + "\n");
    }

    @Test
    public void withdraw100(){
        transact().times(1).withdraw(100);
        callReport();
        assertTransaction(DATE + " | -100.00               | -100.00" + "\n");
    }

    @Test
    public void withdrawMoreThanOnce(){
        transact().times(2).withdraw(100);
        callReport();
        assertTransaction(DATE + " | -100.00               | -100.00" + "\n"+
                                        DATE + " | -100.00               | -200.00" + "\n");
    }

    @Test
    public void printAccountActivityNumberOfTimes(){
        transact().times(2).deposit(100);
        callReport();
        transact().times(1).withdraw(200);
        outContent.reset();
        callReport();
        assertTransaction(DATE + " | 100.00               | 100.00" + "\n"+
                                        DATE + " | 100.00               | 200.00" + "\n" +
                                        DATE + " | -200.00               | 0.00" + "\n");

    }

    private Transactions transact(){
        return new Transactions();
    }

    private class Transactions {

        int times;

        private void deposit(int amount){
            IntStream.range(0, times).boxed().forEach(p -> account.deposit(amount));
        }

        private void withdraw(int amount){
            IntStream.range(0, times).boxed().forEach(p -> account.withdraw(amount));
        }

        private Transactions times(int times){
            this.times = times;
            return this;
        }

    }

}
