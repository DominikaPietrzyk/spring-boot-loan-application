package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.api.controllers.AbstractRestControllerTest;
import loan.application.SpringBootLoanApplication.api.v1.mapper.ClientMapper;
import loan.application.SpringBootLoanApplication.services.ClientService;
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
;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ClientFormControllerTest extends AbstractRestControllerTest {

    @Mock
    ClientService clientService;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    ClientFormController controller;

    @Mock
    ClientMapper clientMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void displayClientForm() throws Exception {
        mockMvc.perform(get("/clientForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void saveClientData() throws Exception {

        mockMvc.perform(post("/clientForm")
                .param("firstName", "Jan")
                .param("lastName", "Kowalski"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/loan"));
    }
}