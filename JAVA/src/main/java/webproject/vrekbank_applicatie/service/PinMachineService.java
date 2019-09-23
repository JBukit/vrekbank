package webproject.vrekbank_applicatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webproject.vrekbank_applicatie.model.PinMachine;
import webproject.vrekbank_applicatie.model.dao.PinMachineDao;

@Service
public class PinMachineService {

    @Autowired
    PinMachineDao pinMachineDao;

    public PinMachine findByDailyConnectIdentifier(int dailyConnectIdentifier) {
        PinMachine p = pinMachineDao.findByDailyConnectIdentifier(dailyConnectIdentifier);
        return p;
    }

    public boolean exists(int dailyConnectIdentifier) {
        return pinMachineDao.existsById(dailyConnectIdentifier);
    }

    public PinMachine findByAddIdentifier(int addIdentifier)
    { PinMachine q = pinMachineDao.findByAddIdentifier(addIdentifier);
    return q;
    }

    public void savePinmachine(PinMachine pinMachine) {pinMachineDao.save(pinMachine);}
}
