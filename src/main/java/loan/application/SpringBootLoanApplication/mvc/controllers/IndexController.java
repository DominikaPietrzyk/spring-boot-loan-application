package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.mvc.viewModels.ClientViewModel;
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
    public String displayClientForm(Model model) {

        model.addAttribute("client", new Client());
        return "index";
    }

    @PostMapping
    public String getClientData(@ModelAttribute @Valid Client client, BindingResult result,
                                Model model) {

        if (result.hasErrors()) {
            return "index";
        }
        clientService.saveClient(client);
        model.addAttribute("loan", new Loan());

        return "redirect:/loanForm" ;
    }



   /* @PostMapping
    public String getClientData(@ModelAttribute @Valid ClientViewModel clientViewModel, BindingResult result,
                                Model model) {

        if (result.hasErrors()) {
            return "index";
        }
        Client client = new Client();
        client.setFirstName(clientViewModel.getFirstName());
        client.setLastName(clientViewModel.getLastName());

        clientService.saveClient(client);
        model.addAttribute("loan", new Loan());

        return "redirect:/loanForm" ;
    }*/


}
