package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.services.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LoanExtensionControllerTest {

    @Mock
    LoanService loanService;

    @InjectMocks
    LoanExtensionController controller;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void displayLoanExtensionForm() throws Exception {
        mockMvc.perform(get("/loanExtension/Form"))
                .andExpect(status().isOk())
                .andExpect(view().name("loanExtensionForm"));

    }

    @Test
    void getClientDataLoanExtensionForm() throws Exception {
        mockMvc.perform(post("/loanExtension/Form")
                .param("id", String.valueOf(4L))
                .param("amount", String.valueOf(1234))
                .param("dueDate", "2021-09-24")
                .param("isLoanDelay", String.valueOf(false)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/loanExtension/4"))
                .andExpect(model().attributeExists("loanLoanExtensionForm"));
    }

    @Test
    void displayLoanExtension() throws Exception {
        mockMvc.perform(get("/loanExtension/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("loanExtensionDialog"));
    }

    @Test
    void updateLoanDate() throws Exception {
        mockMvc.perform(post("/loanExtension/4")
                .param("id", String.valueOf(4L))
                .param("amount", String.valueOf(1234))
                .param("dueDate", "2021-07-28")
                .param("isLoanDelay", String.valueOf(false)))
                .andExpect(status().isOk())
                .andExpect(view().name("loanExtensionConfirmation"));
    }
}