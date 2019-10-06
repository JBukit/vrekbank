package webproject.vrekbank_applicatie.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.PaymentData;
import webproject.vrekbank_applicatie.model.PinMachine;
import webproject.vrekbank_applicatie.model.Transfer;
import webproject.vrekbank_applicatie.model.dao.PinMachineDao;

@Service
public class PinMachineService {

    @Autowired
    PinMachineDao pinMachineDao;

    public PinMachine findByDailyConnectIdentifier(long dailyConnectIdentifier) {
        PinMachine p = pinMachineDao.findByDailyConnectIdentifier(dailyConnectIdentifier);
        return p;
    }

    public boolean addIdentifierIsCorrect(int addIdentifier) {
        if (pinMachineDao.findByAddIdentifier(addIdentifier) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean exists(int addIdentifier) {
        if (pinMachineDao.findByAddIdentifier(addIdentifier) != null) {
            return true;
        } else return false;
    }

    public PinMachine findByAddIdentifier(int addIdentifier) {
        PinMachine q = pinMachineDao.findByAddIdentifier(addIdentifier);
        return q;
    }

    public void savePinmachine(PinMachine pinMachine) {
        pinMachineDao.save(pinMachine);
    }

    public String serialize(PinMachine pinMachine) {
        Gson gson = new Gson();
        String json = gson.toJson(pinMachine);
        return json;
    }

    public String serializeTransfer(Transfer transfer) {
        Gson gson = new Gson();
        String json = gson.toJson(transfer);
        return json;
    }


    public PinMachine deserialize(String json) {
        Gson gson = new Gson();
        PinMachine pinMachine = gson.fromJson(json, PinMachine.class);
        return pinMachine;
    }

    public PaymentData deserializePaymentData(String json) {
        Gson gson = new Gson();
        PaymentData paymentData = gson.fromJson(json, PaymentData.class);
        return paymentData;
    }
}
