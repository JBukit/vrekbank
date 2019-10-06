package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.*;
import webproject.vrekbank_applicatie.service.AccountValidator;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;
import webproject.vrekbank_applicatie.service.PinMachineService;
import webproject.vrekbank_applicatie.service.TransferValidator;

@RestController
public class PinController {

    private static final long INVALIDCODE = 0;

    @Autowired
    BusinessAccountValidator businessAccountValidator;

    @Autowired
    AccountValidator accountvalidator;

    @Autowired
    PinMachineService pinMachineService;

    @Autowired
    TransferValidator transferValidator;

    @GetMapping(value = "pinmachine/pinmachinecanbeadded/{iban}/{addIdentifier}")
    public String pinMachineCanBeAdded(@PathVariable String iban, @PathVariable int addIdentifier) {
        boolean ibanExists = businessAccountValidator.pinMachineExistsByIban(iban);
        if (ibanExists) {
            PinMachine pinMachineWithAddRequest = businessAccountValidator.findByIban(iban).getPinMachine();
            if (addIdentifier == pinMachineWithAddRequest.getAddIdentifier()) {
                return "ok";
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
                return pinMachineWithAddRequest.getDailyConnectIdentifier();
            } else return INVALIDCODE;
        } else return INVALIDCODE;
    }

    // voor betalen elegantere oplossing, met postmapping en json
    Transfer transfer;

    @PostMapping(value = "https://localhost:8080/paymentmachine/payment")
    public String paymentThroughPinMachine(@PathVariable String json) {
        PaymentData paymentData = pinMachineService.deserializePaymentData(json);

        // check iban

        //let op, eerder bestaande functie CREDITibandoesexist is verkeerde benaming voor dit gebruik, nog aanpassen.
        boolean ibanShopperExists = accountvalidator.creditIbanDoesExist(paymentData.getIban());

        //rekening shopper ophalen en referentie aan toe kennen(meerdere keren nodig)
        Account shoppersAccount = accountvalidator.findByIban(paymentData.getIban());
        //check pin . Nog even op eerste eigenaar, niet via lijst rekeninghouders.

        boolean pinShopperIsCorrect = shoppersAccount.getOwner().getPIN() == paymentData.getPin();

        //check saldo

        boolean shopperHasEuros = (shoppersAccount.getBalance() - paymentData.getPaymentAmount()) >=
                shoppersAccount.getMinimumBalance();

        //NB volgens instructies zijn alle pins goed in deze release. Test nu makkelijker:
        pinShopperIsCorrect = true;


        if (ibanShopperExists && shopperHasEuros && pinShopperIsCorrect) {
            transfer.setDebitIban(paymentData.getIban());

            //dit moet nog via proces openen, via een sessie bijv, en hier die pinmachine ophalen
            // Zodat er verschillende pinmachines geopend kunnen zijn
            String shopkeepersIban = pinMachineService.findByDailyConnectIdentifier(13990188).getBusinessAccount().getIban();
            transfer.setCreditIban(shopkeepersIban);
            transfer.setDate("vandaag!");  // datum  nog ophalen
            transfer.setDescription("pinbetaling");
            transfer.setTransferAmount(paymentData.getPaymentAmount());

            transferValidator.saveTransfer(transfer);

            accountvalidator.updateDebitBalance(transfer);

            accountvalidator.updateCreditBalance(transfer);

            json = pinMachineService.serializeTransfer(transfer);

        } else if (!ibanShopperExists) {
        } ////do stuff,, creeer andere json
        else if (!pinShopperIsCorrect) {
        } // do other stuff. Creer andere json. Nb hier komen we nu nooit; hard op true gezet.
        else if (!shopperHasEuros) {
        } // do yet other stuff.. creeer weer andere json

        return json;
    }

}

//eerder experiment, even geparkeerd
//    @GetMapping(value = "/businessAccount/{dailyConnectIdentifier}")
//    public String getAttachedMKBAccount(@PathVariable int dailyConnectIdentifier) {
//        BusinessAccount shopholdersAccount = businessAccountValidator.findByPinMachine
//                (pinMachineService.findByDailyConnectIdentifier(dailyConnectIdentifier));
//        String json = businessAccountValidator.serialize(shopholdersAccount);
//        return json;
//    }

//        @GetMapping(value = "/members/new")
//        public String putMember(@RequestParam String json) {
//            Member member = memberService.deserialize(json);
//            return member.getName() + " OK";
//        }

//    @PostMapping(value = "/businessAccount/addPin/{json}")
//    public String addPinMachine(@PathVariable String json) {
//        PinMachine clientPinMachine = pinMachineService.deserialize(json);
//
//        //controle of er een pinmachine bestaat
//        if (businessAccountValidator.pinMachineExists(clientPinMachine.getBusinessAccount().getIban())) {
//            //check controlegetal
//            if (pinMachineService.addIdentifierIsCorrect(clientPinMachine.getAddIdentifier())) {
//                //stuur daily connect identifier retour (8cijferige code)
//                PinMachine pinMachineinDb = pinMachineService.findByAddIdentifier(clientPinMachine.getAddIdentifier());
//                Long dailyConnectIdentifier = pinMachineinDb.getDailyConnectIdentifier();
//                String dailyConnectIdentifierInString = Long.toString(dailyConnectIdentifier);
//                return "Gelukt!";
//            } else return "AddIdentifierIncorrect";
//        } else return "PinMachineUnknown";
//    }
