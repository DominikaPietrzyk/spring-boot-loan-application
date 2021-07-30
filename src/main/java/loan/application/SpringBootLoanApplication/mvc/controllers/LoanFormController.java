package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.exceptions.CannotCreateLoanException;
import loan.application.SpringBootLoanApplication.services.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class LoanFormController {

    private final LoanService loanService;

    public LoanFormController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/loan")
    public String displayLoanForm(Model model) {
        model.addAttribute("loanForm", new Loan());
        return "loanForm";
    }

    @PostMapping("/loan")
    public String addLoan(@ModelAttribute("loanForm") @Valid Loan loan, BindingResult result,
                          Model model) {

        if (result.hasErrors() || loan.getDueDate() == null) {
            return "loanForm";
        } else {
            try {
                Loan savedLoan = loanService.saveLoan(loan);
                model.addAttribute("loan", savedLoan);

                return "loanConfirmation";
            } catch (CannotCreateLoanException ex) {
                return "loanFormError";
            }
        }
    }
}
