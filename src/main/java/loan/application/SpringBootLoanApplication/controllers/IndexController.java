package loan.application.SpringBootLoanApplication.controllers;

import loan.application.SpringBootLoanApplication.domain.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.validation.Valid;

@Controller
public class IndexController implements WebMvcConfigurer{

    @GetMapping("/")
    public String save(@Valid @ModelAttribute("client") Client client,BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "index";
        }

        return "redirect:/loanConfirmation";
    }

    @RequestMapping("/loanConfirmation")
    public String getIndexPage(Model model) {

        model.addAttribute("loan");
        return "loanConfirmation";
    }
}
