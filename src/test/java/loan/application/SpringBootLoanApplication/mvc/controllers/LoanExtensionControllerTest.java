package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.services.ClientService;
import loan.application.SpringBootLoanApplication.services.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LoanExtensionControllerTest {

    @Mock
    LoanService loanService;

    @Mock
    ClientService clientService;

    LoanExtensionController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        controller = new LoanExtensionController(loanService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void displayLoanExtensionForm() throws Exception {
        mockMvc.perform(get("/loanExtension/Form"))
                .andExpect(status().isOk())
                .andExpect(view().name("loanExtensionForm"));

    }

    @Test
    void getClientDataLoanExtensionForm() throws Exception{
        ClientDTO client = new ClientDTO();
        client.setId(3L);
        client.setFirstName("Jan");
        client.setLastName("Nowak");


        when(clientService.findClientById(ArgumentMatchers.any())).thenReturn(client);

        mockMvc.perform(post("/loanExtension/Form"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/loanExtension/3"))
                .andExpect(model().attributeExists("loanLoanExtensionForm"));
    }


    @Test
    void displayLoanExtension() throws Exception{
        mockMvc.perform(get("/loanExtension/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("loanExtension"));
    }

    @Test
    void updateLoanDate() {
    }
}