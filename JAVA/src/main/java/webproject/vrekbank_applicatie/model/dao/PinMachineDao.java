package webproject.vrekbank_applicatie.model.dao;

import org.springframework.data.repository.CrudRepository;
import webproject.vrekbank_applicatie.model.PinMachine;

public interface PinMachineDao  extends CrudRepository<PinMachine, Integer> {

    public PinMachine findByDailyConnectIdentifier(int dailyConnectIdentifier);

    public PinMachine findByAddIdentifier(int addIdentifier);

}
