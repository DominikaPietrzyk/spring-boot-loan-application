package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.exceptions.CannotCreateLoanException;
import loan.application.SpringBootLoanApplication.exceptions.LoanNotFoundException;
import loan.application.SpringBootLoanApplication.services.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

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
        return "loanExtensionForm" ;
    }

    @PostMapping("/Form")
    public String getClientDataLoanExtensionForm(@ModelAttribute  Loan loan,Model model) {

        Long id = loan.getId();
        loanService.findLoanDtoById(id);

        model.addAttribute("loanLoanExtensionForm", new Loan());

        return "redirect:/loanExtension/" + loan.getId();
    }


    @GetMapping("/{id}")
    public String displayLoanExtension(@PathVariable("id") Long id)  {
        return "loanExtension" ;
    }

    @PostMapping("/{id}")
    public String updateLoanDate(@PathVariable("id") Long id,Model model) throws LoanNotFoundException, CannotCreateLoanException {

        Loan loan = null;
        try {
            loan = loanService.getLoanById(id);
        } catch (LoanNotFoundException e) {
            //TODO: error not found
            return "errorPage";
        }

        if (!loan.isLoanExtension()){

            Calendar cal = Calendar.getInstance();
            cal.setTime(loan.getDueDate());

            cal.add(Calendar.DATE, 14);
            Date modifiedDate = cal.getTime();

            loan.setDueDate(modifiedDate);
            loan.setLoanExtension(true);
        }
        else {
            return "errorPage";
        }
        Loan updatedLoan= null;
        try {
            updatedLoan = loanService.saveLoan(loan);
        } catch (CannotCreateLoanException e) {
            return "errorPage";
        }
        model.addAttribute("updatedLoan",updatedLoan);

        return "loanExtensionConfirmation";
    }
}
