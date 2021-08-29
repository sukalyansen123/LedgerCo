package geektrust.ledger;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LedgerService {
    
    private static TreeMap<String, LoanRequest> bankLoanMap = new TreeMap<String, LoanRequest>();

    private static LedgerService ledger = null;
    
    private LedgerService() {}

    public static LedgerService getInstance(){
        if(ledger == null)
            ledger = new LedgerService();
        return ledger;
    } 

    public static TreeMap<String, LoanRequest> getLoans() {
        return bankLoanMap;
    }

    private static LoanRequest getLoanDetails(String loanMapKey) {
        LoanRequest loan = bankLoanMap.get(loanMapKey);
        
        return loan;
    }

    protected static void addLoan(LoanRequest loan) {
        String loanMapKey = loan.getIdentifier();

        if(bankLoanMap.get(loanMapKey) == null){
            bankLoanMap.put(loanMapKey, loan);
        }
    }

    protected static void makeLumsumPayment(PaymentRequest lumsum) {
        String loanMapKey = lumsum.getIdentifier();
        int emisPaid = lumsum.getEmisPaid();
        int payment = lumsum.getLumsum();
        LoanRequest loan = getLoanDetails(loanMapKey);

         if(loan == null)
            return;

        loan.getLumsumMap().put(emisPaid, payment);
    }

    protected static void showBalance(BalanceRequest balanceRequest) {
        LoanRequest loan = getLoanDetails(balanceRequest.getIdentifier());

        if(loan ==  null)
            return;

        int emiAmount = loan.getEmiAmount();

        //System.out.println(emiAmount + " "+loan.getAmountPaid());
        int totalPaidLoanAmount = 0;
        HashMap<Integer, Integer> lumsumMap = loan.getLumsumMap();
            
        for(Map.Entry<Integer,Integer> mapEntry: lumsumMap.entrySet()) {
                int lumsumPaid = mapEntry.getValue();
                int emiCount = mapEntry.getKey();

                if(emiCount <= balanceRequest.getemisPaid())
                totalPaidLoanAmount+= lumsumPaid;
                else
                break;
        }
        totalPaidLoanAmount += balanceRequest.getemisPaid()*emiAmount;
    

        int totalAmountPayable = (int)(Math.ceil(loan.getTotalAmountPayable() - totalPaidLoanAmount));
        int emisLeft = (int)(Math.ceil((double)totalAmountPayable/emiAmount));
        
        System.out.println(loan.getBankName() + " " + loan.getBorrowerName() +" " + totalPaidLoanAmount +" " + emisLeft);
    }    

}
