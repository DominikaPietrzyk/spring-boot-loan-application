package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.api.v1.mapper.ClientMapper;
import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ClientFormController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public ClientFormController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping("/clientForm")
    public String displayClientForm(Model model) {

        model.addAttribute("client", new Client());
        return "index";
    }

    @PostMapping("/clientForm")
    public String saveClientData(@ModelAttribute @Valid Client client, BindingResult result,
                                 Model model) {

        if (result.hasErrors()) {
            return "index";
        } else {
            model.addAttribute("loan", clientService.createNewClient(clientMapper.clientToClientDTO(client)));
            return "redirect:/loan";
        }

    }
}
