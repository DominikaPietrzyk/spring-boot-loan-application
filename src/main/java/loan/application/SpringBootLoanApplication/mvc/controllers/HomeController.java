package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.domain.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping({"", "/", "/home"})
@Controller
public class HomeController {

    @GetMapping
    public String displayStart(Model model) {
        model.addAttribute("client", new Client());
        return "start";
    }

}
