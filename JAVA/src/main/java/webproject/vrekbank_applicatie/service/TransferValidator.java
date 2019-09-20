package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.model.dao.BusinessAccountDao;
import webproject.vrekbank_applicatie.model.dao.TransferDao;
import java.util.*;


@Service
public class TransferValidator {

    private final int TOP10 = 10;

    @Autowired
    TransferDao transferDao;

    @Autowired
    BusinessAccountDao businessAccountDao;

    public TransferValidator() {super();}

    public void saveTransfer (Transfer transfer) {
        transferDao.save(transfer);
    }

    //Method to find transfer by creditIban
    public List<Transfer> findByCreditIban (String creditIban){
        List <Transfer> transfers;
        transfers = transferDao.findByCreditIban(creditIban);
        return transfers;
    }

    //Method to find transfer by deditIban
    public List <Transfer> findByDebitIban (String debitIban){
        List <Transfer> transfers;
        transfers = transferDao.findByDebitIban(debitIban);
        return transfers;
    }


    //Method to find all transfers
    public List<Transfer> findAllTransfers (){
        List <Transfer> transfers;
        transfers = transferDao.findAll();
        return transfers;
    }

    //Method to find all MKB transfers
    public List<Transfer> findAllMKBTransfers (){
        List <Transfer> mkbTransfers = new ArrayList<>();
        List <Transfer> allTransfers = findAllTransfers();
        for (Transfer transfer: allTransfers) {
            if (businessAccountDao.existsByIban(transfer.getCreditIban()) ||
                    businessAccountDao.existsByIban(transfer.getDebitIban())) {
                mkbTransfers.add(transfer);
            }
        }

        //Print check all transactions
        System.out.println("Printen van de lijst allTransfers");
        for(Transfer transfer: allTransfers) {
            System.out.println(transfer.getDescription());
        }

        //Print check only mkb transactions
        System.out.println("Printen van de lijst mkbTransfers");
        for(Transfer transfer: mkbTransfers) {
            System.out.println(transfer.getDescription());
        }

        return mkbTransfers;
    }

    //Method to count number of MKB transfers per MKB account
    public List<BusinessAccount> top10MostMKBTransfers() {

        List<Transfer> mkbTransfers = findAllMKBTransfers();

        HashMap<String, Integer> mkbTransferHashMap = new HashMap<>();

        //Insert MKB credit transactions in HashMap
        for (Transfer transfer : mkbTransfers) {

            String creditIban = transfer.getCreditIban();

            Integer previousAmount = mkbTransferHashMap.get(creditIban);

            System.out.println("Waarde van previousamount " + previousAmount);

            if (previousAmount == null) {
                mkbTransferHashMap.put(creditIban, 1);
            } else {
                mkbTransferHashMap.put(creditIban, previousAmount + 1);
            }
        }

        System.out.println("Is HashMap Empty? "+ mkbTransferHashMap.isEmpty());

        //Print check unsorted
        for (String i : mkbTransferHashMap.keySet()) {
            System.out.println("Ongesorteerd - key: " + i + " value: " + mkbTransferHashMap.get(i));
        }

        //Sort the list
        List<Map.Entry<String, Integer>> l = new ArrayList(mkbTransferHashMap.entrySet());
        Collections.sort(l, Collections.reverseOrder(Map.Entry.comparingByValue()));

        //Print check sorted
        for (Map.Entry<String, Integer> e : l) {
            System.out.println("Gesorteerd - key: " + e.getKey() + " value: " + e.getValue());
        }

        //Show top 10 highest value accounts (most credit spendings)
        List<String> list10 = new ArrayList<>();
        int size = 0;
        if (l.size() < TOP10) {
            size = l.size();
        } else {
            size = TOP10;
        }
        for (int i = 0; i < size; i++ ) {
            list10.add(l.get(i).getKey());
        }
        System.out.println(list10);

        //Find matching account based on String iban from list10
        List<BusinessAccount> ba = new ArrayList<>();
        for (int i = 0; i < list10.size(); i++) {
            ba.add(businessAccountDao.findByIban(list10.get(i)));
        }

        return ba;
    }



}


