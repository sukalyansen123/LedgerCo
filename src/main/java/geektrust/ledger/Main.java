package geektrust.ledger;

import java.io.File;

public class Main {
    
    public static void main(String[] args) {
        try{
            String ledgerFilePath = args[0];
            File file = new File(ledgerFilePath);
            CommandProcessor commandProcessor = new CommandProcessor();
            commandProcessor.processCommandsFile(file);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("FILE PATH NOT FOUND");
        }
    }

}
