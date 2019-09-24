package pinMachine;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientPinMachineService {

    PinMachineDao pinMachineDao;

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

    public void addPinMachineRequest(ClientPinMachine clientPinMachine) {
        URL existsUrl;
        HttpURLConnection con;
        try {

            String json = serialize(clientPinMachine);

            existsUrl = new URL("http://localhost:8080/businessAccount/exists/" + json);
            con = (HttpURLConnection) existsUrl.openConnection();
            con.setRequestMethod("GET");
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
