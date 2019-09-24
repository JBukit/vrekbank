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

    public static void main(String[] args) {
        Scanner pinScanner = new Scanner(System.in);
        System.out.println("Als u uw pinautomaat voor het eerst wil aansluiten, type dan 'koppelen' ");

        String addPinMachine = pinScanner.next();
        if (addPinMachine.equals("koppelen")){
            System.out.println("Aan welke zakelijke IBAN wilt u uw pinautomaat koppelen?");
            String inputIban = pinScanner.next();
            System.out.println("Wat is het vijfcijferige controlegetal dat u van de bank heeft ontvangen?");
            int inputAddIdentifier = pinScanner.nextInt();

            ClientPinMachine clientPinMachine = new ClientPinMachine(0, inputAddIdentifier, inputIban);
            PinController pinController = new PinController();
            pinController.doesExistRequest(inputAddIdentifier);


        } else {
            System.out.println("commando onbekend");
        }

//
//        //client.run(id);

    }

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

    private void doesExistRequest(int addIdentifier) {
        URL existsUrl;
        HttpURLConnection con;
        try {
            existsUrl = new URL("http://localhost:8080/businessAccount/exists/" + addIdentifier);
            con = (HttpURLConnection) existsUrl.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            if (code == 200) {                        ///?????
                BufferedReader response = getResponse(con);
                String answer = response.readLine();

                switch(answer) {
                    case "yes":
                        System.out.println("Deze pinmachine met identifier" + addIdentifier +
                                "is geregistreerd bij een MBK rekening");
                        break;
                    case "no":
                        System.out.println("Helaas, geen rekening met identifier" + addIdentifier + " gevonden...");
                        break;
                    default:
                        System.out.println("Rare zooi, er is iets onbekends en onbegrijpelijks gebeuren. " +
                                "Ik adviseer in paniek gillend rondrennen.");
                }
            } else {
                System.out.println("Server connection problem. Status code: " + code);
            }
        } catch (IOException ioe) {
            System.out.println("IO Exception while connecting to server.");
            System.out.println(ioe.getCause());
            System.exit(-1000);
        }
    }

    private BufferedReader getResponse(HttpURLConnection con) throws IOException {
        InputStream response;
        InputStreamReader reader;
        BufferedReader in;
        response = con.getInputStream();
        reader = new InputStreamReader(response);
        in = new BufferedReader(reader);
        return in;
    }
}


