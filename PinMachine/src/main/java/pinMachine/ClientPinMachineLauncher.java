package pinMachine;

import pinMachine.controller.ClientPinMachineController;
import pinMachine.service.ClientPinMachineService;
import pinMachine.model.PinMachineDao;

public class ClientPinMachineLauncher {
    private ClientPinMachineController controller;
    private ClientPinMachineService service;
    private PinMachineDao dao;

    public ClientPinMachineLauncher() {
        super();
    }

    public static void main(String[] args) {
        ClientPinMachineLauncher launcher = new ClientPinMachineLauncher();
        launcher.setup();
        launcher.launch();
    }

    private void setup() {
        controller = new ClientPinMachineController();
        service = new ClientPinMachineService();
        dao = new PinMachineDao();

        controller.setService(service);
        service.setDao(dao);
    }

    private void launch() {
        controller.loopForCommand();
    }
}