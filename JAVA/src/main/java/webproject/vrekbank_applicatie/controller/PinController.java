package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.PinMachine;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;
import webproject.vrekbank_applicatie.service.PinMachineService;

@RestController
public class PinController {

    private static final int INVALIDCODE = 0;

    @Autowired
    BusinessAccountValidator businessAccountValidator;

    @Autowired
    PinMachineService pinMachineService;

    @GetMapping(value = "pinmachine/pinmachinecanbeadded/{iban}/{addIdentifier}")
    public String pinMachineCanBeAdded(@PathVariable String iban, @PathVariable int addIdentifier) {
        boolean ibanExists = businessAccountValidator.pinMachineExistsByIban(iban);
        if (ibanExists) {
            PinMachine pinMachineWithAddRequest = businessAccountValidator.findByIban(iban).getPinMachine();
            if (addIdentifier == pinMachineWithAddRequest.getAddIdentifier()) {
                return "ok"; // dailyConnectIdentifierInString moet hier mee, maar hoe afpellen voor switch in client?
            } else return "AddIdentifierNotCorrect";
        } else return "NoIbanFound";
    }

    //alternatief voor functies hieronder en hierboven is een string sturen met zowel een ok als daarna de code, en die
// string door client laten splitsen.
    @GetMapping(value = "pinmachine/getdailyconnectidentifier/{iban}/{addIdentifier}")
    public long getDailyConnectIdentifier(@PathVariable String iban, @PathVariable int addIdentifier) {
        boolean ibanExists = businessAccountValidator.pinMachineExistsByIban(iban);
        if (ibanExists) {
            PinMachine pinMachineWithAddRequest = businessAccountValidator.findByIban(iban).getPinMachine();
            if (addIdentifier == pinMachineWithAddRequest.getAddIdentifier()) {
                return (long) pinMachineWithAddRequest.getDailyConnectIdentifier();
            } else return INVALIDCODE;
        } else return INVALIDCODE;
    }

    //eerder experiment, even geparkeerd
    @GetMapping(value = "/businessAccount/{dailyConnectIdentifier}")
    public String getAttachedMKBAccount(@PathVariable int dailyConnectIdentifier) {
        BusinessAccount shopholdersAccount = businessAccountValidator.findByPinMachine
                (pinMachineService.findByDailyConnectIdentifier(dailyConnectIdentifier));
        String json = businessAccountValidator.serialize(shopholdersAccount);
        return json;
    }

//        @GetMapping(value = "/members/new")
//        public String putMember(@RequestParam String json) {
//            Member member = memberService.deserialize(json);
//            return member.getName() + " OK";
//        }


    @PostMapping(value = "/businessAccount/addPin/{json}")
    public String addPinMachine(@PathVariable String json) {
        PinMachine clientPinMachine = pinMachineService.deserialize(json);

        //controle of er een pinmachine bestaat
        if (businessAccountValidator.pinMachineExists(clientPinMachine.getBusinessAccount().getIban())) {
            //check controlegetal
            if (pinMachineService.addIdentifierIsCorrect(clientPinMachine.getAddIdentifier())) {
                //stuur daily connect identifier retour (8cijferige code)
                PinMachine pinMachineinDb = pinMachineService.findByAddIdentifier(clientPinMachine.getAddIdentifier());
                long dailyConnectIdentifier = pinMachineinDb.getDailyConnectIdentifier();
                String dailyConnectIdentifierInString = Long.toString(dailyConnectIdentifier);
                return "Gelukt!";
            } else return "AddIdentifierIncorrect";
        } else return "PinMachineUnknown";
    }
}