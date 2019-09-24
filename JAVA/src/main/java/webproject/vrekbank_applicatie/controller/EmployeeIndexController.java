
package webproject.vrekbank_applicatie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import webproject.vrekbank_applicatie.model.*;
import webproject.vrekbank_applicatie.service.BusinessAccountValidator;
import webproject.vrekbank_applicatie.service.EmployeeValidator;
import java.math.BigDecimal;
import webproject.vrekbank_applicatie.service.TransferValidator;


import java.util.List;


@Controller
@SessionAttributes({"name"})
public class EmployeeIndexController {

    private final String FUNCTION_HEAD_MKB = "Hoofd MKB";

    @Autowired
    EmployeeValidator employeeValidator;

    @Autowired
    BusinessAccountValidator BusinessAccountValidator;

    @Autowired
    TransferValidator transferValidator;


    @GetMapping (value = "employeeLogin")
    public String EmployeeLogin (@ModelAttribute Employee loggedInEmployee, Model model) {
        Employee employee = new Employee();
        return "EmployeeLogin";
    }

    @GetMapping (value = "employeeRegister")
    public String EmployeeIndexRegistrationHandler () {
        return "EmployeeRegistration";
    }

    @GetMapping (value = "employeeIndexWithKnownName")
    public String overviewHandler (@SessionAttribute ("name") String userName, Model model) {
        Employee employee = employeeValidator.findEmployeeByUserName(userName);
        EmployeeLogin(employee, model);
        return "MKBOverview";
    }


    @GetMapping (value = "MKBHighestBalance")
    public String MKBHighestBalanceHandler (@SessionAttribute("name") String name,
                                            @ModelAttribute Employee loggedInEmployee, Model model) {
    // find employee based on inlog-information (userName en PW)
        Employee employee = employeeValidator.findEmployeeByUserName(name);
        // if function = Hoofd MKB, show 10 accounts with highest balance
        if (employee.getTypeFunction().equals(FUNCTION_HEAD_MKB)) {
            List<BusinessAccount> top10 = BusinessAccountValidator.showTop10BusinessAccounts();
            model.addAttribute("top10", top10);
            return "MKBHighestBalance";
        }
        return "MKBHighestBalance";
    }


    @GetMapping (value = "MKBMostTransfers")
    public String MKBMostTransfersHandler (@SessionAttribute("name") String name, Model model) {
        model.containsAttribute(name);

        List<BusinessAccount> top10 = transferValidator.top10MostMKBTransfers();

        model.addAttribute("top10", top10);
        return "MKBMostTransfers";
    }


    @GetMapping (value = "MKBSectorView")
    public String MKBSectorViewHandler (Model model){

     // deze handler geeft per sector het aantal accounts, de som van de saldos en het gemiddelde saldo door

        List<BusinessAccount> sectorViewBouw = BusinessAccountValidator.findBusinessAccountsBySector("bouw");
        model.addAttribute("numberOfAccountsBouw", sectorViewBouw.size());

        Double totalBalanceBouw = BusinessAccountValidator.findTotalBalanceBySector("bouw");
        model.addAttribute("totalBalanceBouw", (String.format("€ %.2f", totalBalanceBouw)));

        if (sectorViewBouw.size() != 0){
            Double meanBalanceBouw = (totalBalanceBouw / sectorViewBouw.size());
            model.addAttribute("meanBalanceBouw", (String.format("€ %.2f", meanBalanceBouw)));
        } else {
            model.addAttribute("meanBalanceBouw", (String.format("€ %.2f", 0.0)));
        }


        List<BusinessAccount> sectorViewFinancien = BusinessAccountValidator.findBusinessAccountsBySector("financien");
        model.addAttribute("numberOfAccountsFinancien", sectorViewFinancien.size());

        Double totalBalanceFinancien = BusinessAccountValidator.findTotalBalanceBySector("financien");
        model.addAttribute("totalBalanceFinancien", (String.format("€ %.2f", totalBalanceFinancien)));

        if (sectorViewFinancien.size() != 0){
            Double meanBalanceFinancien = (totalBalanceFinancien / sectorViewFinancien.size())  ;
            model.addAttribute("meanBalanceFinancien", (String.format("€ %.2f",meanBalanceFinancien)));
        } else {
            model.addAttribute("meanBalanceFinancien", (String.format("€ %.2f", 0.0)));
        }


        List<BusinessAccount> sectorViewHandel = BusinessAccountValidator.findBusinessAccountsBySector("handel");
        model.addAttribute("numberOfAccountsHandel", sectorViewHandel.size());

        Double totalBalanceHandel = BusinessAccountValidator.findTotalBalanceBySector("handel");
        model.addAttribute("totalBalanceHandel", (String.format("€ %.2f", totalBalanceHandel)));

        if (sectorViewHandel.size() != 0){
            Double meanBalanceHandel = (totalBalanceHandel / sectorViewHandel.size());
            model.addAttribute("meanBalanceHandel", (String.format("€ %.2f", meanBalanceHandel)));
        } else {
            model.addAttribute("meanBalanceHandel", (String.format("€ %.2f", 0.0)));
        }


        List<BusinessAccount> sectorViewHoreca = BusinessAccountValidator.findBusinessAccountsBySector("horeca");
        model.addAttribute("numberOfAccountsHoreca", sectorViewHoreca.size());

        Double totalBalanceHoreca = BusinessAccountValidator.findTotalBalanceBySector("horeca");
        model.addAttribute("totalBalanceHoreca", (String.format("€ %.2f", totalBalanceHoreca)));

        if (sectorViewHoreca.size() != 0){
            Double meanBalanceHoreca = (totalBalanceHoreca / sectorViewHoreca.size());
            model.addAttribute("meanBalanceHoreca", (String.format("€ %.2f", meanBalanceHoreca)));
        } else {
            model.addAttribute("meanBalanceHoreca", (String.format("€ %.2f", 0.0)));
        }


        List<BusinessAccount> sectorViewIndustrie = BusinessAccountValidator.findBusinessAccountsBySector("industrie");
        model.addAttribute("numberOfAccountsIndustrie", sectorViewIndustrie.size());

        Double totalBalanceIndustrie = BusinessAccountValidator.findTotalBalanceBySector("industrie");
        model.addAttribute("totalBalanceIndustrie", (String.format("€ %.2f", totalBalanceIndustrie)));

        if (sectorViewIndustrie.size() != 0){
            Double meanBalanceIndustrie = (totalBalanceIndustrie / sectorViewIndustrie.size());
            model.addAttribute("meanBalanceIndustrie", (String.format("€ %.2f", meanBalanceIndustrie)));
        } else {
            model.addAttribute("meanBalanceIndustrie", (String.format("€ %.2f", 0.0)));
        }


        List<BusinessAccount> sectorViewLandbouw = BusinessAccountValidator.findBusinessAccountsBySector("landbouw");
        model.addAttribute("numberOfAccountsLandbouw", sectorViewLandbouw.size());

        Double totalBalanceLandbouw = BusinessAccountValidator.findTotalBalanceBySector("landbouw");
        model.addAttribute("totalBalanceLandbouw", (String.format("€ %.2f", totalBalanceLandbouw)));

        if (sectorViewLandbouw.size() != 0){
            Double meanBalanceLandbouw = (totalBalanceLandbouw / sectorViewLandbouw.size());
            model.addAttribute("meanBalanceLandbouw", (String.format("€ %.2f", meanBalanceLandbouw)));
        } else {
            model.addAttribute("meanBalanceLandbouw", (String.format("€ %.2f", 0.0)));
        }


        List<BusinessAccount> sectorViewLogistiek = BusinessAccountValidator.findBusinessAccountsBySector("logistiek");
        model.addAttribute("numberOfAccountsLogistiek", sectorViewLogistiek.size());

        Double totalBalanceLogistiek = BusinessAccountValidator.findTotalBalanceBySector("logistiek");
        model.addAttribute("totalBalanceLogistiek", (String.format("€ %.2f", totalBalanceLogistiek)));

        if (sectorViewLogistiek.size() != 0){
            Double meanBalanceLogistiek = (totalBalanceLogistiek / sectorViewLogistiek.size());
            model.addAttribute("meanBalanceLogistiek", (String.format("€ %.2f", meanBalanceLogistiek)));
        } else {
            model.addAttribute("meanBalanceLogistiek", (String.format("€ %.2f", 0.0)));
        }


        List<BusinessAccount> sectorViewOnderwijs = BusinessAccountValidator.findBusinessAccountsBySector("onderwijs");
        model.addAttribute("numberOfAccountsOnderwijs", sectorViewOnderwijs.size());

        Double totalBalanceOnderwijs = BusinessAccountValidator.findTotalBalanceBySector("onderwijs");
        model.addAttribute("totalBalanceOnderwijs", (String.format("€ %.2f", totalBalanceOnderwijs)));

        if (sectorViewOnderwijs.size() != 0){
            Double meanBalanceOnderwijs = (totalBalanceOnderwijs / sectorViewOnderwijs.size());
            model.addAttribute("meanBalanceOnderwijs", (String.format("€ %.2f", meanBalanceOnderwijs)));
        } else {
            model.addAttribute("meanBalanceOnderwijs", (String.format("€ %.2f", 0.0)));
        }


        List<BusinessAccount> sectorViewIt = BusinessAccountValidator.findBusinessAccountsBySector("it");
        model.addAttribute("numberOfAccountsIt", sectorViewIt.size());

        Double totalBalanceIt = BusinessAccountValidator.findTotalBalanceBySector("it");
        model.addAttribute("totalBalanceIt", (String.format("€ %.2f", totalBalanceIt)));

        if (sectorViewIt.size() != 0){
            Double meanBalanceIt = (totalBalanceIt / sectorViewIt.size());
            model.addAttribute("meanBalanceIt", (String.format("€ %.2f", meanBalanceIt)));
        } else {
            model.addAttribute("meanBalanceIt", (String.format("€ %.2f", 0.0)));
        }
        

        List<BusinessAccount> sectorViewZorg = BusinessAccountValidator.findBusinessAccountsBySector("zorg");
        model.addAttribute("numberOfAccountsZorg", sectorViewZorg.size());

        Double totalBalanceZorg = BusinessAccountValidator.findTotalBalanceBySector("zorg");
        model.addAttribute("totalBalanceZorg", (String.format("€ %.2f", totalBalanceZorg)));

        if (sectorViewZorg.size() != 0){
            Double meanBalanceZorg = (totalBalanceZorg / sectorViewZorg.size());
            model.addAttribute("meanBalanceZorg", (String.format("€ %.2f", meanBalanceZorg)));
        } else {
            model.addAttribute("meanBalanceZorg", (String.format("€ %.2f", 0.0)));
        }


        return "MKBSectorView";
    }
}
