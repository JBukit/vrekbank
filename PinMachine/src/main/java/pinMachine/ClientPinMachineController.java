package pinMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ClientPinMachineController {

    public ClientPinMachineController() {
        super();
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
}


