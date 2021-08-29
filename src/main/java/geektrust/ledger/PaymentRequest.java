package geektrust.ledger;

public class PaymentRequest {
    private String bankName;
    private String borrowerName;
    private int lumsumAmount;
    private int emisPaid;

    public PaymentRequest(
        String bankName,
        String borrowerName,
        int lumsumAmount,
        int emisPaid
    ) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.lumsumAmount = lumsumAmount;
        this.emisPaid = emisPaid;
    }

    public String getIdentifier(){
        return this.bankName + '|' + this.borrowerName;
    }

    public int getEmisPaid(){
        return this.emisPaid;
    }

    public int getLumsum() {
        return this.lumsumAmount;
    }
}
