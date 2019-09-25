package pinMachine;

import com.google.gson.Gson;
import org.hibernate.service.spi.InjectService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientPinMachineService {

    public String serialize(ClientPinMachine clientPinMachine) {
        Gson gson = new Gson();
        String json = gson.toJson(clientPinMachine);
        return json;
    }

    public ClientPinMachine deserialize(String json) {
        Gson gson = new Gson();
        ClientPinMachine clientPinMachine = gson.fromJson(json, ClientPinMachine.class);
        return clientPinMachine;
    }

    public void doExistRequest(int addIdentifier) {
        URL existsUrl;
        HttpURLConnection con;
        try {
            existsUrl = new URL("http://localhost:8080/pinmachine/exists/" + addIdentifier);
            con = (HttpURLConnection) existsUrl.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            if (code == 200) {
                BufferedReader response = getResponse(con);
                String answer = response.readLine();

                switch (answer) {
                    case "yes":
                        System.out.println("Betert, pinautomaat met koppelcode " + addIdentifier + " bestaat!");
                        break;
                    case "no":
                        System.out.println("Booeee... Geen lid met koppelcode " + addIdentifier + " gevonden...");
                        break;
                    default:
                        System.out.println("Wat? De server is gek geworden");
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

    public void addPinMachineRequest(ClientPinMachine clientPinMachine) {
        URL addPinUrl;
        HttpURLConnection con;
        try {
            String json = serialize(clientPinMachine);

            System.out.println(json);

            addPinUrl = new URL("http://localhost:8080/businessAccount/addPin/" + json);
            con = (HttpURLConnection) addPinUrl.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8"); // toevoeging
            con.setRequestProperty("Accept", "application/json"); //toevoeging
            con.setDoOutput(true); //toevoeging
            int code = con.getResponseCode();
            if (code == 200) {
                BufferedReader response = getResponse(con);
                String answer = response.readLine();

                switch (answer) {
                    case "Gelukt!":
                        System.out.println("Deze pinmachine met identifier" + clientPinMachine.getAddIdentifier() +
                                "is geregistreerd bij een MBK rekening");
                        break;
                    case "no":
                        System.out.println("Helaas, geen gekoppeling met identifier" + clientPinMachine.getAddIdentifier());
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
