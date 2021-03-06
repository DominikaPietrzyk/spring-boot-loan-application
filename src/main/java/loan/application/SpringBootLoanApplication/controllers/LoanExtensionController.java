package loan.application.SpringBootLoanApplication.controllers;

import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.repositories.LoanRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class LoanExtensionController {

    private final LoanRepository loanRepository;

    public LoanExtensionController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }




    @GetMapping("/loanExtension")
    public String displayLoanExtension(Model model) {
        model.addAttribute("loan", new Loan());
        return "loanExtension";
    }

    @PostMapping("/loanExtension")
    public String updateLoanDate(@Valid @ModelAttribute("loan") Loan loan, BindingResult bindingResult,
                                 Model model){

        if(bindingResult.hasErrors()){
            return "loanExtension";
        }

        Loan updatedLoan= loanRepository.save(loan);
        model.addAttribute("updatedLoan",updatedLoan);

        return "loanExtensionConfirmation";
    }
}
