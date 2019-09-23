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

    private static final int IWANTFIVEDIGITS = 10000;
    private static final int IWANTEIGHTDIGITS = 10000000;

    private String pinMachineAddedBefore = " aan deze rekening al een pinmachine gekoppeld was. " +
            "Een tweede toevoegen is op dit moment niet mogelijk. " +
            "Wacht op een volgende release en/of stort " +
            "astronomische bedragen naar het VrekBank Ontwikkelteam...";
    private String ibanUnknown = " dit een ons niet bekend rekeningnummer is; daar kunnen we geen pinautomaat aan hangen.";

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

        if (businessAccountValidator.exists(addPinMachineRequest.getIban())) {

            BusinessAccount businessAccount = businessAccountValidator.findByIban(addPinMachineRequest.getIban());
            if (businessAccount.getPinMachine() != null) {
                model.addAttribute("PinMachineAddedBefore", pinMachineAddedBefore);
                return "AddpinMachineFailed";
            }

            // generate pin with 5 digits and check if does not yet exist in db
            int generatedAddIdentifier = 0;
            Random firstObjGenerator = new Random();
            do {
                generatedAddIdentifier = firstObjGenerator.nextInt(90000) + IWANTFIVEDIGITS;
            }
            while (pinMachineService.findByAddIdentifier(generatedAddIdentifier) != null);

            int generatedDailyConnectIdentifier = 0;
            Random secondObjGenerator = new Random();
            do {
                generatedDailyConnectIdentifier = secondObjGenerator.nextInt(90000000) + IWANTEIGHTDIGITS;
            }
            while (pinMachineService.findByDailyConnectIdentifier(generatedDailyConnectIdentifier) != null);


            PinMachine pinMachine = new PinMachine(generatedDailyConnectIdentifier, generatedAddIdentifier, businessAccount);

            businessAccount.setPinMachine(pinMachine);

            pinMachine.setBusinessAccount(businessAccount);

            businessAccountValidator.saveBusinessAccount(businessAccount);

            pinMachineService.savePinmachine(pinMachine);

            model.addAttribute("pinMachine", pinMachine);

            return "AddPinMachineConfirmation";
        } else
            model.addAttribute("IbanUnknown", ibanUnknown);
        return "AddPinMachineFailed";
    }
}
