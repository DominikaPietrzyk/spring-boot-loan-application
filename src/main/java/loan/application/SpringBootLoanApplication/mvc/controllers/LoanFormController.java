package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.services.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RequestMapping("/loanForm")
@Controller
public class LoanFormController {

    private final LoanService loanService;

    public LoanFormController(LoanService loanService) {
        this.loanService = loanService;
    }

   @GetMapping
    public String displayLoanForm(Model model) {
        model.addAttribute("loanForm", new Loan());
        return "loanForm" ;
    }

    @PostMapping
    public String addLoan(@ModelAttribute @Valid Loan loan, BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            return "loanForm";
        }
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int hourOfGettingLoan= calendar.get(Calendar.HOUR_OF_DAY);

       if(hourOfGettingLoan < 24 && hourOfGettingLoan > 6){
           Loan savedLoan =  loanService.saveLoan(loan);
           model.addAttribute("loan", savedLoan);
       }
       else {
           return "loanFormError";
       }

        return "loanConfirmation";
    }
}
