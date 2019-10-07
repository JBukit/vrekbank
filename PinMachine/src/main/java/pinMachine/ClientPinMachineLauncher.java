package pinMachine;

import pinMachine.controller.ClientPinMachine;
import pinMachine.controller.ClientPinMachineController;
import pinMachine.model.dao.PaymentDao;
import pinMachine.service.ClientPinMachineService;
import pinMachine.model.dao.PinMachineDao;
import pinMachine.service.PaymentService;

public class ClientPinMachineLauncher {
    private ClientPinMachineController controller;
    private ClientPinMachineService service;
    private PaymentService paymentService;
    private PinMachineDao dao;
    private PaymentDao paymentDao;
    private ClientPinMachine clientPinMachine;

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
        ///
        paymentService = new PaymentService();
        paymentDao = new PaymentDao();

        controller.setPaymentService(paymentService);
        paymentService.setPaymentDao(paymentDao);

    }

    private void launch() {
        controller.loopForCommand();
    }
}