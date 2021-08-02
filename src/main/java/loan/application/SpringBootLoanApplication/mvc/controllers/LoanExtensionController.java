package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.exceptions.CannotCreateLoanException;
import loan.application.SpringBootLoanApplication.exceptions.LoanNotFoundException;
import loan.application.SpringBootLoanApplication.services.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/loanExtension")
@Controller
public class LoanExtensionController {

    private final LoanService loanService;

    public LoanExtensionController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/Form")
    public String displayLoanExtensionForm(Model model) {
        model.addAttribute("clientLoanExtensionForm", new Loan());

        return "loanExtensionForm";
    }

    @PostMapping("/Form")
    public String getLoanIdForLoanExtensionForm(@ModelAttribute @Valid Loan loan, Model model, BindingResult result) {

            Long id = loan.getId();

            if(result.hasErrors() || id==null){
                return "loanExtensionForm";
            }

            loanService.findLoanDtoById(id);

            model.addAttribute("loanLoanExtensionForm", new Loan());

            return "redirect:/loanExtension/" + loan.getId();
    }

    @GetMapping("/{id}")
    public String displayLoanExtensionDialog(@PathVariable("id") Long id) {
        return "loanExtensionDialog";
    }

    @PostMapping("/{id}")
    public String updateLoanDate(@PathVariable("id") Long id, Model model) throws LoanNotFoundException, CannotCreateLoanException {

        Loan loan;
        try {
            loan = loanService.getLoanById(id);

        } catch (LoanNotFoundException e) {
            System.out.println(e);
            return "errorPage";
        }

        Loan updatedLoan = null;
        try {
            updatedLoan = loanService.updateLoan(loan);
        } catch (CannotCreateLoanException e) {
            return "errorPage";
        }
        model.addAttribute("updatedLoan", updatedLoan);

        return "loanExtensionConfirmation";
    }
}
