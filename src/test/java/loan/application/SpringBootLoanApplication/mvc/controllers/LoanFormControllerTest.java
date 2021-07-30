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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class LoanFormControllerTest {

    @Mock
    LoanService loanService;

    @InjectMocks
    LoanFormController controller;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void displayLoanForm() throws Exception {
        mockMvc.perform(get("/loan"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("loanForm"))
                .andExpect(view().name("loanForm"));

    }

    @Test
    void addLoan() throws Exception {
        mockMvc.perform(post("/loan")
                .param("amount", String.valueOf(1234))
                .param("dueDate", "2021-09-24")
                .param("isLoanDelay", String.valueOf(false)))
                .andExpect(status().isOk())
                .andExpect(view().name("loanConfirmation"));
    }
}