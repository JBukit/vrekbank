package pinMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PinController {

    public PinController() {
        super();

    }

    ClientPinMachineService clientPinMachineService;

    public static void main(String[] args) { ;

        Scanner pinScanner = new Scanner(System.in);
        System.out.println("Als u uw pinautomaat voor het eerst wil aansluiten, type dan 'koppelen' ");

        String addPinMachine = pinScanner.next();
        if (addPinMachine.equals("koppelen")) {
            System.out.println("Aan welke zakelijke IBAN wilt u uw pinautomaat koppelen?");
            String inputIban = pinScanner.next();
            System.out.println("Wat is het vijfcijferige controlegetal dat u van de bank heeft ontvangen?");
            int inputAddIdentifier = pinScanner.nextInt();

            ClientPinMachine clientPinMachine = new ClientPinMachine(0, inputAddIdentifier, inputIban);

          //  clientPinMachineService.addPinMachineRequest(clientPinMachine);

        } else {
            System.out.println("commando onbekend");
        }

//        //client.run(id);

    };

    private void run(int pinMachineIdentifier) {
        URL url;
        HttpURLConnection con;
        StringBuffer content = null;
        try {
            url = new URL("http://localhost:8080/businessAccount/" + pinMachineIdentifier);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (content != null) {
            System.out.println(content.toString());
        }
    }

}


