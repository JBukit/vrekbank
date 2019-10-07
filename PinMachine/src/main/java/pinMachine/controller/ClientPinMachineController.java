package pinMachine.controller;

import pinMachine.model.PaymentData;
import pinMachine.service.ClientPinMachineService;
import pinMachine.service.PaymentService;

import java.io.IOException;
import java.util.Scanner;

public class ClientPinMachineController {

    private ClientPinMachineService service;

    private PaymentService paymentService;

    public ClientPinMachineController() {
        super();
    }

    private String status = "closed";

    Scanner pinScanner = new Scanner(System.in);

    public void loopForCommand() {
        System.out.println();
        System.out.println("Wat wilt u met deze fantastische pinautomaat doen? ");
        System.out.println();
        System.out.println("Voor eerste keer aansluiten, type 'koppelen'.");
        System.out.println("Voor dagelijks aanmelden, type 'openen'.");
        System.out.println("Voor betalen, type 'betalen'.");
        System.out.println();
        System.out.println("Voor stoppen, type 'stop'."); // nog opzoeken
        String command = pinScanner.next();
        do {
            if (command.equals("koppelen")) {
                addPinMachine();
                loopForCommand();
            }
            if (command.equals("openen")) {
                open();
                loopForCommand();
            }
            if (command.equals("betalen")) {
                try {
                    pay();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loopForCommand();
            }
        } while (!command.equals("stop"));
        System.out.println("De pinmachine wordt gesloten.");
    }

    //userstory het eenmalig koppelen van nieuwe automaat
    private void addPinMachine() {
        System.out.println("Aan welke zakelijke IBAN wilt u uw pinautomaat koppelen?");
        String inputIban = pinScanner.next();
        System.out.println("Wat is het vijfcijferige controlegetal dat u van de bank heeft ontvangen?");
        int inputAddIdentifier = pinScanner.nextInt();
        service.pinMachineIsAdded(inputIban, inputAddIdentifier);
    }

//        if (dailyConnectIdentifierToInsert >= 0) {
//            clientPinMachine.setDailyConnectIdentifier(dailyConnectIdentifierToInsert);
//
//            service.getDao().saveClientPinMachine(clientPinMachine);
//
//            System.out.println("Uw pinautomaat is nu gekoppeld.");
//            System.out.println("De 8cijferige code die uw pinmachine nodig heeft om " +
//                    "zich dagelijks bij de bank aan te melden is in uw locale database opgeslagen.");
//            System.out.println();

    //userstory 2: dagelijks aanmelding van de automaat (met de 8 cijfers) en journaal starten
    private boolean open() {
        if (true == true)  // hier nog request inbouwen met gebruik van dailyidentifier uit lokale database.
        // object pinmachine obhalen, json van maken, versturen, door bank laten checken en reactie.

        // tevens start journaalpagina doen. (alternatief; list met bladzijden overslaan en aan einde dag selectie
        // draaien op datum, daarmee de juiste transfers ophalen en naar console printen.)
        {
            status = "opened";
            System.out.println("Winkelier, uw pinmachine is geopend, u kunt betalingen laten doen ");
            return true;
        } else
            return false;
    }

    //onderstaande nog uitbreiden wanneer open werkt; happy flow if(open), else
    // );
    private void pay() throws IOException {
        if (status.equals("closed")) {
            System.out.println("Helaas, u moet de pinautomaat eerst openen (dagelijk aanmelden), " +
                    "voordat er betaald kan worden");
        } else if (status.equals("opened")) {
            //Vanaf hier: User story 3: Betalen
            System.out.println("Aan winkelier: Wat is het bedrag dat de klant moet betalen?");
            double amountClientNeedsToPay = pinScanner.nextDouble();
            System.out.println("Beste klant, wat is uw rekeningnummer (IBAN) ?");
            String ibanClientForPinPayment = pinScanner.next();
            System.out.println("Aan klant: Voer nu uw pincode in");
            int pincodeClientForPinPayment = pinScanner.nextInt();

            PaymentData paymentData;
            paymentData = new PaymentData(ibanClientForPinPayment, pincodeClientForPinPayment, amountClientNeedsToPay);

            System.out.println("iban: " + paymentData.getIban());
            System.out.println("pint:" + paymentData.getPin());
            System.out.println("bedrag" + paymentData.getPaymentAmount());
            System.out.println("id: " + paymentData.getPaymentId());

            paymentService.paymentRequest(paymentData);
        }
    }

//    private void run(int pinMachineIdentifier) {
//        URL url;
//        HttpURLConnection con;
//        StringBuffer content = null;
//        try {
//            url = new URL("http://localhost:8080/businessAccount/" + pinMachineIdentifier);
//            con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (content != null) {
//            System.out.println(content.toString());
//        }
//    }

    public ClientPinMachineService getService() {
        return service;
    }

    public void setService(ClientPinMachineService service) {
        this.service = service;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Scanner getPinScanner() {
        return pinScanner;
    }

    public void setPinScanner(Scanner pinScanner) {
        this.pinScanner = pinScanner;
    }
}
