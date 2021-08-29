package geektrust.ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BalanceRequestTest {
    private String bankName = "GSO";
    private String borrowerName = "Cobb";
    private BalanceRequest balanceRequest = new BalanceRequest(bankName, borrowerName, 10);

    @Test
    void testUniqueIdentiferLoanRequest() {
        assertEquals("GSO|Cobb", balanceRequest.getIdentifier());
    }
}
