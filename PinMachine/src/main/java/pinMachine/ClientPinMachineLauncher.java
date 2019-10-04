package pinMachine;

import pinMachine.controller.ClientPinMachine;
import pinMachine.controller.ClientPinMachineController;
import pinMachine.service.ClientPinMachineService;
import pinMachine.model.PinMachineDao;

import java.util.Scanner;

public class ClientPinMachineLauncher {

    private ClientPinMachineController controller;
    private ClientPinMachineService service;

    public ClientPinMachineLauncher() {
        super();
    }

    public static void main(String[] args) {
        ClientPinMachineLauncher launcher = new ClientPinMachineLauncher();
        launcher.setup();
        launcher.launch();
    }

    private void setup() {
        controller = new ClientPinMachineController();
        service = new ClientPinMachineService();

      //  controller.setService(service);
    }

    private void launch() {
        Scanner pinScanner = new Scanner(System.in);

        //userstory het eenmalig koppelen van nieuwe automaat
        System.out.println("Beste winkelier, als u uw pinautomaat voor het eerst wil aansluiten, type dan 'koppelen' ");

        String addPinMachine = pinScanner.next();
        if (addPinMachine.equals("koppelen")) {
            System.out.println("Aan welke zakelijke IBAN wilt u uw pinautomaat koppelen?");
            String inputIban = pinScanner.next();
            System.out.println("Wat is het vijfcijferige controlegetal dat u van de bank heeft ontvangen?");
            int inputAddIdentifier = pinScanner.nextInt();

            ClientPinMachine clientPinMachine = new ClientPinMachine(0, inputAddIdentifier, inputIban);
            ClientPinMachineService clientPinMachineService = new ClientPinMachineService();

            //onderstaande functie haalt tevens een functie aan die de 8cijferige code ophaalt
            long dailyConnectIdentifierToInsert = clientPinMachineService.pinMachineCanBeAdded(inputIban, inputAddIdentifier);
            clientPinMachine.setDailyConnectIdentifier(dailyConnectIdentifierToInsert);

            PinMachineDao pinMachineDao = new PinMachineDao();
            pinMachineDao.saveClientPinMachine(clientPinMachine);

            System.out.println("Uw pinautomaat is nu gekoppeld.");
            System.out.println("De 8cijferige code die uw pinmachine nodig heeft om " +
                    "zich dagelijks bij de bank aan te melden is in uw locale database opgeslagen.");

            System.out.println();

            //clientPinMachineService.doesExistRequest(inputAddIdentifier);
            //clientPinMachineService.addPinMachineRequest(clientPinMachine);

            //        client.run(id);

        } else {
            System.out.println("commando onbekend");
        }

        //userstory 2: dagelijks aanmelding van de automaat (met de 8 cijfers) en journaal starten

        //Vanaf hier: User story 3: Betalen
        System.out.println("Aan winkelier: Als de klant met pin wil betalen, type dan 'betalen' ");
        String clientWantsToPay = pinScanner.next();
        if (clientWantsToPay.equals("betalen")) {
            System.out.println("Aan winkelier: Wat is het bedrag dat de klant moet betalen?");
            float amountClientNeedsToPay = pinScanner.nextFloat();
            System.out.println("Beste klant, wat is uw rekeningnummer (IBAN) ?");
            String ibanClientForPinPayment = pinScanner.next();
            System.out.println("Aan klant: Voer nu uw pincode in");
            int pincodeClientForPinPayment = pinScanner.nextInt();

        }
    }
}
