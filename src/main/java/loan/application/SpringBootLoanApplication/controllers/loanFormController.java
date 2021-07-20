package loan.application.SpringBootLoanApplication.controllers;

import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.repositories.LoanRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class loanFormController {

    private final LoanRepository loanRepository;

    public loanFormController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping("/loanForm")
    public String loanForm(Model model) {
        model.addAttribute("loan", new Loan());
        return "loanForm";
    }

    @PostMapping("/loanForm")
    public String addLoan(@ModelAttribute @Valid Loan loan, BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            return "loanForm";
        }
        Loan savedLoan = loanRepository.save(loan);

        model.addAttribute("loan", savedLoan);

        return "loanConfirmation";
    }

}
