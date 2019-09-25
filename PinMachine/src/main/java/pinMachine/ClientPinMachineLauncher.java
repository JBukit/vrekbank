package pinMachine;

import java.util.Scanner;

public class ClientPinMachineLauncher {

    public static void main(String[] args) {



        Scanner pinScanner = new Scanner(System.in);
        System.out.println("Als u uw pinautomaat voor het eerst wil aansluiten, type dan 'koppelen' ");

        String addPinMachine = pinScanner.next();
        if (addPinMachine.equals("koppelen")) {
            System.out.println("Aan welke zakelijke IBAN wilt u uw pinautomaat koppelen?");
            String inputIban = pinScanner.next();
            System.out.println("Wat is het vijfcijferige controlegetal dat u van de bank heeft ontvangen?");
            int inputAddIdentifier = pinScanner.nextInt();

            ClientPinMachine clientPinMachine = new ClientPinMachine(0, inputAddIdentifier, inputIban);

            ClientPinMachineService clientPinMachineService = new ClientPinMachineService();


            clientPinMachineService.doExistRequest(inputAddIdentifier);
            //clientPinMachineService.addPinMachineRequest(clientPinMachine);



            //        client.run(id);

        } else {
            System.out.println("commando onbekend");
        }
    }
}
