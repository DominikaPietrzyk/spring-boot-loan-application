package loan.application.SpringBootLoanApplication.controllers;

import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.services.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/loanForm")
@Controller
public class loanFormController {

    private final LoanService loanService;

    public loanFormController(LoanService loanService) {
        this.loanService = loanService;
    }

   @GetMapping
    public String displayLoanForm(Model model) {
        model.addAttribute("loan", new Loan());
        return "loanForm" ;
    }

    @PostMapping
    public String addLoan(@ModelAttribute @Valid Loan loan, BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            return "loanForm";
        }

        Loan savedLoan =  loanService.saveLoan(loan);
        model.addAttribute("loan", savedLoan);

        return "loanConfirmation";
    }

}
