package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webproject.vrekbank_applicatie.model.BusinessAccount;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;

@RestController
public class EmployeeOverviewController {

    @Autowired
    BusinessAccountValidator businessAccountValidator;

//    @GetMapping(value = "/businessAccount/{}")
//    public String getAttachedMKBAccount(@PathVariable int dailyConnectIdentifier)  {
//        BusinessAccount shopholdersAccount = businessAccountValidator.findBusinessAccountByDailyConnectIdentifier(dailyConnectIdentifier);
//        String json = businessAccountValidator.serialize(shopholdersAccount);
//        return json;
//    }

//        @GetMapping(value = "/members/new")
//        public String putMember(@RequestParam String json) {
//            Member member = memberService.deserialize(json);
//            return member.getName() + " OK";
//        }
//
//        @GetMapping(value = "/members/exists/{id}")
//        public String existsMember(@PathVariable int id) {
//            boolean exists = memberService.exists(id);
//            if (exists) {
//                return "yes";
//            }
//            return "no";
//        }
}
