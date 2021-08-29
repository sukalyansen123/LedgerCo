package geektrust.ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test; 

public class PaymentRequestTest {
    private String bankName = "GSO";
    private String borrowerName = "Cobb";
    private PaymentRequest payment = new PaymentRequest(bankName, borrowerName, 5000, 5);

    @Test
    void testUniqueIdentiferLoanRequest() {
        assertEquals("GSO|Cobb", payment.getIdentifier());
    }
}
