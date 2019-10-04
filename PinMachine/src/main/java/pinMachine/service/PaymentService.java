package pinMachine.service;

import com.google.gson.Gson;
import pinMachine.controller.ClientPinMachine;
import pinMachine.model.PaymentData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PaymentService {


    public String serialize(PaymentData paymentData) {
        Gson gson = new Gson();
        String json = gson.toJson(paymentData);
        return json;
    }

    public void PaymentRequest(PaymentData paymentData) throws IOException {

        String json =  serialize(paymentData);

        System.out.println(json); //check

        URL obj = new URL("https://localhost:8080/paymentmachine/payment");

        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

        postConnection.setRequestMethod("POST");

        postConnection.setRequestProperty("userId", "a1bcdefgh");

        postConnection.setRequestProperty("Content-Type", "application/json");

        postConnection.setDoOutput(true);

        OutputStream os = postConnection.getOutputStream();

        os.write(json.getBytes());

        os.flush();

        os.close();

        int responseCode = postConnection.getResponseCode();

        System.out.println("POST Response Code :  " + responseCode);

        System.out.println("POST Response Message : " + postConnection.getResponseMessage());

        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success

            BufferedReader in = new BufferedReader(new InputStreamReader(

                    postConnection.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {

                response.append(inputLine);

            } in .close();

            // print result

            System.out.println(response.toString());

        } else {

            System.out.println("POST NOT WORKED");

        }

    }
}
