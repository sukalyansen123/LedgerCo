package geektrust.ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LedgerTestService {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void Setup() {
        LedgerService.getInstance();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanUp() {
        System.setOut(originalOut);
    }

    @Test
    void addLoanInsertsLoanwithUniqueKey() {
        LoanRequest loan = new LoanRequest("ABC", "Jill", 1000, 5, 10);
        LedgerService.addLoan(loan);
        assertEquals(loan, LedgerService.getLoans().get(loan.getIdentifier()));
    }

    @Test 
    void addLoanFailsWhenLoanwithSameKeyRequested() {
        LoanRequest loan = new LoanRequest("ABC", "Jack", 10, 1, 2);
        LedgerService.addLoan(loan);
        LoanRequest newLoan = new LoanRequest("ABC", "Jack", 100, 10, 12);
        assertNotEquals(newLoan, LedgerService.getLoans().get(loan.getIdentifier()));
    }

    @Test
    void makeLumsumPaymentAddsPaymentforExistingLoan() {
        LoanRequest loan = new LoanRequest("PQR", "Heath", 1000, 5, 10);
        LedgerService.addLoan(loan);
        PaymentRequest pay = new PaymentRequest("PQR", "Heath", 50, 2);
        LedgerService.makeLumsumPayment(pay);
        assertEquals(50, loan.getLumsumMap().get(2));
    }

    @Test
    void makeLumsumPaymentFailsforNonExistingLoan() {
        LoanRequest loan = new LoanRequest("PQRS", "Heath", 5000, 5, 2);
        PaymentRequest pay = new PaymentRequest("PQRS", "Heath", 50, 2);
        LedgerService.makeLumsumPayment(pay);
        assertEquals(null, loan.getLumsumMap().get(2));
    }

    @Test
    void testShowBalance() {
        LoanRequest loan = new LoanRequest("ACL", "Ledger", 1000, 5, 10);
        LedgerService.addLoan(loan);
        PaymentRequest pay = new PaymentRequest("PQR", "Ledger", 50, 2);
        LedgerService.makeLumsumPayment(pay);
        BalanceRequest balanceRequest = new BalanceRequest("ACL", "Ledger", 5);
        LedgerService.showBalance(balanceRequest);
        assertEquals("ACL Ledger 125 55\n", outContent.toString());
    }

}
