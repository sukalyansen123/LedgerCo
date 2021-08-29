package geektrust.ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoanRequestTest {

    private String bankName = "GSO";
    private String borrowerName = "Cobb";
    private LoanRequest loanRequest = new LoanRequest(bankName, borrowerName, 10000, 5, 2);

    @Test
    void testUniqueIdentiferLoanRequest() {
        assertEquals("GSO|Cobb", loanRequest.getIdentifier());
    }

    @Test 
    void testEmiValue() {
        assertEquals(184, loanRequest.getEmiAmount());
    }
}