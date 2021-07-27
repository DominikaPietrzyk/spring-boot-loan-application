package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.services.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class LoanFormControllerTest {

    @Mock
    LoanService loanService;

    LoanExtensionController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new LoanExtensionController(loanService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void displayLoanForm() throws Exception {
        mockMvc.perform(get("/loanForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("loanForm"));

    }

    @Test
    void addLoan() {
    }
}