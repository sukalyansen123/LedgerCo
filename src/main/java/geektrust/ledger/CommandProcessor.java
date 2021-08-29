package geektrust.ledger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandProcessor {
    
    LedgerService ledger = LedgerService.getInstance();

    void processCommandsFile(File file) {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                //System.out.println("COMMAND:"+ command);
                parseCommands(command);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT PRESENT");
        }
    }

    void parseCommands(String command) {
        String[] commandParams = command.split(" ");
        CommandTypes commandType = CommandTypes.valueOf(commandParams[0]);
        //System.out.println(commandType);
        String bankName = commandParams[1];
        String borrowerName = commandParams[2];        

        switch (commandType) {
            case LOAN:
                //System.out.println("LOAN");
                int principalAmount = Integer.parseInt(commandParams[3]); 
                int loanTenure = Integer.parseInt(commandParams[4]);
                int rateOfInterest = Integer.parseInt(commandParams[5]);
                LoanRequest loan = new LoanRequest(bankName, borrowerName, principalAmount, loanTenure, rateOfInterest);
                LedgerService.addLoan(loan);
                break;
            case PAYMENT:
                //System.out.println("PAYMENT");
                int lumsumAmount = Integer.parseInt(commandParams[3]);
                int emisPaid = Integer.parseInt(commandParams[4]);
                PaymentRequest payment = new PaymentRequest(bankName, borrowerName, lumsumAmount, emisPaid);
                LedgerService.makeLumsumPayment(payment);
                break;
            case BALANCE:
                //System.out.println("BALANCE");
                int emiCount = Integer.parseInt(commandParams[3]);
                BalanceRequest balanceRequest = new BalanceRequest(bankName, borrowerName, emiCount);
                LedgerService.showBalance(balanceRequest); 
                break;               
        }

    }
}
