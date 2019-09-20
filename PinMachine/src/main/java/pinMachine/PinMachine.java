package pinMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PinMachine {

    public PinMachine() {
        super();
    }

    public static void main(String[] args) {
        PinMachine pinMachine = new PinMachine();

        //client.run(id);
        pinMachine.doesExistRequest(1);
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

    private void doesExistRequest(int pinMachindIdentifier) {
        URL existsUrl;
        HttpURLConnection con;
        try {
            existsUrl = new URL("http://localhost:8080/businessAccount/exists/" + pinMachindIdentifier);
            con = (HttpURLConnection) existsUrl.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            if (code == 200) {                        ///?????
                BufferedReader response = getResponse(con);
                String answer = response.readLine();

                switch(answer) {
                    case "yes":
                        System.out.println("Deze pinmachine met identifier" + pinMachindIdentifier +
                                "is geregistreerdt bij een MBK rekening");
                        break;
                    case "no":
                        System.out.println("Helaas, geen rekening met identifier" + pinMachindIdentifier + " gevonden...");
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


