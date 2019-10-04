package pinMachine.service;

import com.google.gson.Gson;
import pinMachine.controller.ClientPinMachine;
import pinMachine.model.PinMachineDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientPinMachineService {

    private PinMachineDao dao;

    private final static long INVALIDCODE = 0;

    private static int clientDailyConnectIdentifier = 0;

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

    //testfunctie, niet meer in gebruik?
//    public void doesExistRequest(int addIdentifier) {
//        URL existsUrl;
//        HttpURLConnection con;
//        try {
//            existsUrl = new URL("http://localhost:8080/pinmachine/exists/" + addIdentifier);
//            con = (HttpURLConnection) existsUrl.openConnection();
//            con.setRequestMethod("GET");
//            int code = con.getResponseCode();
//            if (code == 200) {
//                BufferedReader response = getResponse(con);
//                String answer = response.readLine();
//
//                switch (answer) {
//                    case "yes":
//                        System.out.println("Betert, pinautomaat met koppelcode " + addIdentifier + " bestaat!");
//                        break;
//                    case "no":
//                        System.out.println("Booeee... Geen pinautomaat met koppelcode " + addIdentifier + " gevonden...");
//                        break;
//                    default:
//                        System.out.println("Geen idee, er is wat mis...");
//                }
//            } else {
//                System.out.println("Server connection problem. Status code: " + code);
//            }
//        } catch (IOException ioe) {
//            System.out.println("IO Exception while connecting to server.");
//            System.out.println(ioe.getCause());
//            System.exit(-1000);
//        }
//    }

    // testfunctie, niet meer in gebruik?
//    public void pinDoesExistRequest(String iban) {
//        URL existsUrl;
//        HttpURLConnection con;
//        try {
//            existsUrl = new URL("http://localhost:8080/pinmachine/existsbyiban/" + iban);
//            System.out.println(existsUrl);
//            con = (HttpURLConnection) existsUrl.openConnection();
//            con.setRequestMethod("GET");
//            int code = con.getResponseCode();
//            if (code == 200) {
//                BufferedReader response = getResponse(con);
//                String answer = response.readLine();
//
//                switch (answer) {
//                    case "yes":
//                        System.out.println("Betert, pinautomaat voor rekeningg " + iban + " bestaat!");
//                        break;
//                    case "no":
//                        System.out.println("Booeee... Deze rekening " + iban + " bestaat niet, of heeft geen pinautomaat");
//                        break;
//                    default:
//                        System.out.println("Geen idee, dr is wat mis...");
//                }
//            } else {
//                System.out.println("Server connection problem. Status code: " + code);
//            }
//        } catch (IOException ioe) {
//            System.out.println("IO Exception while connecting to server.");
//            System.out.println(ioe.getCause());
//            System.exit(-1000);
//        }
//    }

    public boolean pinMachineIsAdded(String iban, int addIdentifier) {
        URL existsUrl;
        HttpURLConnection con;
        try {
            existsUrl = new URL("http://localhost:8080/pinmachine/pinmachinecanbeadded/" + iban + "/" + addIdentifier);
            con = (HttpURLConnection) existsUrl.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            if (code == 200) {
                BufferedReader response = getResponse(con);
                String answer = response.readLine();
                switch (answer) {
                    case "ok":
                        System.out.println();
                        System.out.println("Betert, pinautomaat voor rekening " + iban + " bestaat!");
                        System.out.println("Daarnaast heeft u ook nog de juiste 5 cijferige koppelcode ingevoerd.");
                        System.out.println();
                        // functie aanroeopen om met tweede http request de 8cijferige code op te halen
                        ClientPinMachineService clientPinMachineService = new ClientPinMachineService();
                        long dailyConnectIdentifierReceivedFromServer =
                                clientPinMachineService.getDailyConnectIdentifierFromServer(iban, addIdentifier);

                        ClientPinMachine clientPinMachine = new ClientPinMachine(dailyConnectIdentifierReceivedFromServer,
                                addIdentifier, iban);
                        dao.saveClientPinMachine(clientPinMachine);

                        System.out.println();
                        System.out.println("Uw pinautomaat is nu gekoppeld.");
                        System.out.println("De 8cijferige code die uw pinmachine nodig heeft om " +
                                "zich dagelijks bij de bank aan te melden is in uw locale database opgeslagen.");
                        System.out.println();
                        return true;
                    case "NoIbanFound":
                        System.out.println("Helaas... Deze rekening " + iban + " bestaat niet, of heeft geen pinautomaat.");
                        break;
                    case "AddIdentifierNotCorrect":
                        System.out.println("Jammer... De ingevoerde 5 cijferige koppelcode " + addIdentifier + " is niet correct.");
                        break;
                    default:
                        System.out.println("Rare zooi, er is iets onbekends en onbegrijpelijks gebeurd. " +
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
        return false;
    }

    // functie die aangeroepen wordt na check
    public long getDailyConnectIdentifierFromServer(String iban, int addIdentifier) {
        URL existsUrl;
        HttpURLConnection con;
        try {
            existsUrl = new URL("http://localhost:8080/pinmachine/getdailyconnectidentifier/" + iban + "/" + addIdentifier);
            con = (HttpURLConnection) existsUrl.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            if (code == 200) {
                BufferedReader response = getResponse(con);
                String dailyConnectIdentifierReceivedFromServerInString = response.readLine();
                long dailyConnectIdentifierReceivedFromServer = Long.parseLong(dailyConnectIdentifierReceivedFromServerInString);
                return dailyConnectIdentifierReceivedFromServer;
            } else {
                System.out.println("Server connection problem. Status code: " + code);
            }
        } catch (IOException ioe) {
            System.out.println("IO Exception while connecting to server.");
            System.out.println(ioe.getCause());
            System.exit(-1000);
        }
        return INVALIDCODE;
    }

    //volgende stap; objecten via json uitwisselen
//    public void addPinMachineRequest(ClientPinMachine clientPinMachine) {
//        URL addPinUrl;
//        HttpURLConnection con;
//        try {
//            String json = serialize(clientPinMachine);
//
//            System.out.println(json);
//
//            addPinUrl = new URL("http://localhost:8080/businessAccount/addPin/" + json);
//            con = (HttpURLConnection) addPinUrl.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Type", "application/json; utf-8"); // toevoeging
//            con.setRequestProperty("Accept", "application/json"); //toevoeging
//            con.setDoOutput(true); //toevoeging
//            int code = con.getResponseCode();
//            if (code == 200) {
//                BufferedReader response = getResponse(con);
//                String answer = response.readLine();
//
//                switch (answer) {
//                    case "Gelukt!":
//                        System.out.println("Deze pinmachine met identifier" + clientPinMachine.getAddIdentifier() +
//                                "is geregistreerd bij een MBK rekening");
//                        break;
//                    case "no":
//                        System.out.println("Helaas, geen gekoppeling met identifier" + clientPinMachine.getAddIdentifier());
//                        break;
//                    default:
//                        System.out.println("Rare zooi, er is iets onbekends en onbegrijpelijks gebeuren. " +
//                                "Ik adviseer in paniek gillend rondrennen.");
//                }
//            } else {
//                System.out.println("Server connection problem. Status code: " + code);
//            }
//        } catch (IOException ioe) {
//            System.out.println("IO Exception while connecting to server.");
//            System.out.println(ioe.getCause());
//            System.exit(-1000);
//        }
    //  }

    private BufferedReader getResponse(HttpURLConnection con) throws IOException {
        InputStream response;
        InputStreamReader reader;
        BufferedReader in;
        response = con.getInputStream();
        reader = new InputStreamReader(response);
        in = new BufferedReader(reader);
        return in;
    }

    public PinMachineDao getDao() {
        return dao;
    }

    public void setDao(PinMachineDao dao) {
        this.dao = dao;
    }
}
