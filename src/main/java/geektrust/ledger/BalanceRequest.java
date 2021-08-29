package geektrust.ledger;

public class BalanceRequest {
    private String bankName;
    private String borrowerName;
    private int emisPaid;

    public BalanceRequest(
        String bankName,
        String borrowerName,
        int emisPaid
    ){
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.emisPaid = emisPaid;
    }

    public String getBankName(){
        return this.bankName;
    }

    public String getBorrowerName(){
        return this.borrowerName;
    }
    
    public int getemisPaid(){
        return this.emisPaid;
    }

    public String getIdentifier() {
        return this.bankName +  '|' + this.borrowerName;
    }
    
}
