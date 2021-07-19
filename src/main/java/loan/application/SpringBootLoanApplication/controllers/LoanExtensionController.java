package loan.application.SpringBootLoanApplication.controllers;

import loan.application.SpringBootLoanApplication.domain.Loan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class LoanExtensionController {

    @RequestMapping("/loanExtensionConfirmation")
    public String getLoanConfirmation(Model model) {

        return "loanExtensionConfirmation";
    }

    @GetMapping("/loanExtension")
    public String save(@Valid @ModelAttribute("loan") Loan loan, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "loanExtension";
        }

        return "loanExtension";
       // return "redirect:/loanExtensionConfirmation";
    }
}
