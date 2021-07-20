package loan.application.SpringBootLoanApplication.controllers;

import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.repositories.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;

@Controller
public class IndexController{

    private final ClientRepository clientRepository;

    public IndexController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/")
    public String displayFormClient(Model model) {
        Client client = new Client();
        model.addAttribute(client);
        return "index";
    }

    @PostMapping("/")
    public String addLoan(@ModelAttribute @Valid Client client, BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            return "index";
        }
        clientRepository.save(client);
        model.addAttribute("loan", new Loan());
        return "redirect:/loanForm";
    }


}
