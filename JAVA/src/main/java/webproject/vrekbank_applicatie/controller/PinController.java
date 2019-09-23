package webproject.vrekbank_applicatie.controller;

import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;
import webproject.vrekbank_applicatie.service.PinMachineService;

@RestController
public class PinController {

    BusinessAccountValidator businessAccountValidator;

    PinMachineService pinMachineService;

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

    @GetMapping(value = "/businessAccount/exists/{dailyConnectIdentifier}")
    public String existsBusinessAccount(@PathVariable int dailyConnectIdentifier) {
        boolean exists = pinMachineService.exists(dailyConnectIdentifier);
        if (exists) {
            return "yes";
        }
        return "no";
    }
}
