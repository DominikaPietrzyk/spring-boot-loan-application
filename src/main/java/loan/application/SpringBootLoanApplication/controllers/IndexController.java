package loan.application.SpringBootLoanApplication.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        return "index";
    }
    @RequestMapping("/loanConfirmation")
    public String getLoanConfirmation(Model model) {

        return "loanConfirmation";
    }
}
