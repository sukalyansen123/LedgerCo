package geektrust.ledger;

import java.util.HashMap;

public class LoanRequest {
    private String bankName;
    private String borrowerName;
    private int principalAmount;
    private int loanTenure;
    private int rateOfInterest;

    private HashMap<Integer, Integer> emiLumsumMap;

    public LoanRequest(
        String bankName,
        String borrowerName,
        int principalAmount,
        int loanTenure,
        int rateOfInterest
    ) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principalAmount = principalAmount;
        this.loanTenure = loanTenure;
        this.rateOfInterest = rateOfInterest;
        this.emiLumsumMap = new HashMap<Integer,Integer>();
    }

    public int getTenure() {
        return this.loanTenure;
    }

    public int getPrincipal() {
        return this.principalAmount;
    }

    public int getRateOfInterest() {
        return this.rateOfInterest;
    }

    public String getBankName() {
        return this.bankName;
    }

    public String getBorrowerName() {
        return this.borrowerName;
    }
    
    public HashMap<Integer, Integer> getLumsumMap() {
        return this.emiLumsumMap;
    }

    
    public String getIdentifier() {
        return this.bankName+'|'+this.borrowerName;
    }

    public double getTotalAmountPayable() {
        double totalInterest = this.principalAmount*this.loanTenure*this.rateOfInterest/100;
        double totalPayable = this.principalAmount + totalInterest;
    
        return totalPayable;
    }

    public int getEmiAmount() {
        double totalPayable = this.getTotalAmountPayable();
        int emiAmount = (int)(Math.ceil(totalPayable/(12*this.loanTenure)));

        return emiAmount;
    }
}
 