package pinMachine.service;

import com.google.gson.Gson;
import pinMachine.model.Transfer;
import pinMachine.model.dao.PaymentDao;
import pinMachine.model.PaymentData;
import pinMachine.model.dao.TransferDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PaymentService {

private PaymentDao paymentDao;

private TransferDao transferDao;

    public String serialize(PaymentData paymentData) {
        Gson gson = new Gson();
        String json = gson.toJson(paymentData);
        return json;
    }

    public Transfer deserializeTransfer(String json) {
        Gson gson = new Gson();
        Transfer transfer = gson.fromJson(json, Transfer.class);
        return transfer;
    }

    public void paymentRequest(PaymentData paymentData) throws IOException {

        URL obj = new URL("https://localhost:8080/paymentmachine/payment");

        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

        postConnection.setRequestMethod("POST");

        //  postConnection.setRequestProperty("userId", "a1bcdefgh");`

        postConnection.setRequestProperty("Content-Type", "application/json; utf-8");

        postConnection.setRequestProperty("Accept", "application/json");

        postConnection.setDoOutput(true);

        String json =  serialize(paymentData);
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

            System.out.println("Dit was de reactie : " + response.toString());

            Transfer receivedTransfer = deserializeTransfer(response.toString());

            if(receivedTransfer.getDebitIban().equals("Betaling gefaald.")) {
                System.out.println("Aan Winkelier: " + receivedTransfer.getDebitIban());  // algemene foutmelding
                System.out.println("Aan Klant: " + receivedTransfer.getDescription()); // specifieke reden

            } else {transferDao.saveTransfer(receivedTransfer);}

        } else {

            System.out.println("POST FAILED");

        }

    }

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
}
