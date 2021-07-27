package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

@RequestMapping({"", "/", "/index"})
@Controller
public class IndexController{

    private final ClientService clientService;

    public IndexController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String displayStart(Model model)
    {
        model.addAttribute("client", new Client());
        return "start";
    }

    @GetMapping("/clientForm")
    public String displayClientForm(Model model) {

        model.addAttribute("client", new Client());
        return "index";
    }

    @PostMapping("/clientForm")
    public String getClientData(@ModelAttribute @Valid Client client, BindingResult result,
                                Model model) {

        if (result.hasErrors()) {
            return "index";
        }

        model.addAttribute("loan", clientService.saveClient(client));
        return "redirect:/loanForm" ;
    }
}
