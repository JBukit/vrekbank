package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.AddPinMachineRequest;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.model.PinMachine;
import webproject.vrekbank_applicatie.service.AccountValidator;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;
import webproject.vrekbank_applicatie.service.PinMachineService;

import java.util.Random;

@Controller
@SessionAttributes({"name"})
public class MKBOverviewController {

    @Autowired
    BusinessAccountValidator businessAccountValidator;

    @Autowired
    PinMachineService pinMachineService;

    @Autowired
    AccountValidator accountValidator;

    // hier @SessionAttribute ook nodig?
    @PostMapping(value = "AddPinMachineConfirmation")
    public String addPinMachineHandler(@ModelAttribute AddPinMachineRequest
            addPinMachineRequest, Model model) {
        System.out.println("Nu zit ik in de handler!!");
        if (businessAccountValidator.exists(addPinMachineRequest.getIban())) {
            System.out.println( "En nu zit ik in de ifstatement; het iban is herkend!");
            // generate pin with 5 digits and check if does not yet exist in db
            int generatedAddIdentifier = 0;
            Random firstObjGenerator = new Random();
            while (generatedAddIdentifier == 0 || pinMachineService.findByAddIdentifier(generatedAddIdentifier) != null) {
                generatedAddIdentifier = firstObjGenerator.nextInt(100000);
            }

            System.out.println("Eerste randomizer: " + generatedAddIdentifier);


            int generatedDailyConnectIdentifier = 0;
            Random secondObjGenerator = new Random();
            while (generatedDailyConnectIdentifier == 0 || pinMachineService.findByDailyConnectIdentifier
                    (generatedDailyConnectIdentifier) != null) {
                generatedDailyConnectIdentifier = secondObjGenerator.nextInt(100000000);
            }

            System.out.println("voorbij de tweede randomizer: " + generatedDailyConnectIdentifier);

            BusinessAccount businessAccount = businessAccountValidator.findByIban(addPinMachineRequest.getIban());
            System.out.println("account opgehaald!");

            PinMachine pinMachine = new PinMachine(generatedDailyConnectIdentifier, generatedAddIdentifier, businessAccount);

            System.out.println("Nieuwe pinmachine aangemaakt");

            businessAccount.setPinMachine(pinMachine);

            System.out.println("pinmachine set in acocount");

            pinMachine.setBusinessAccount(businessAccount);
            System.out.println("pinmachine aangepast met nieuwe account er in.");

            //pinMachineService.savePinmachine(pinMachine);
            //System.out.println("pinmachine in db opgeslagen");

           // businessAccountValidator.saveBusinessAccount(businessAccount);
            //System.out.println("account in db gezet");

            model.addAttribute("pinMachine", pinMachine);
            System.out.println( "pinmachine in model gezet");
            // return to confirmation screen.

            return "AddPinMachineConfirmation";
        } else
            return "AddPinMachineFailed";
    }
}
