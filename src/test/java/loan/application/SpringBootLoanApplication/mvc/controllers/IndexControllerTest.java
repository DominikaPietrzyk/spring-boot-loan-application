package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    ClientService clientService;

    @Mock
    Model model;

    @Autowired
    MockMvc mockMvc;

    IndexController controller;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(clientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testDisplayStart() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("start"));
    }

    @Test
    public void testDisplayClientForm() throws Exception {
        mockMvc.perform(get("/clientForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

   /* @Test
    void testGetClientData() throws Exception {
       // MockMvcBuilders.standaloneSetup(controller).build();
        when(clientService.saveClient(ArgumentMatchers.any())).thenReturn(Client.builder().id(1L)
                .firstName("Jan").lastName("Kowalski").build());

        mockMvc.perform(post("/clientForm"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/loanForm"))
                .andExpect(model().attributeExists("loan"));
    }*/
    @Test
    public void testSaveOrUpdate() throws Exception {
        Client client = new Client();
        client.setId(3L);
        client.setFirstName("Jan");
        client.setLastName("Nowak");

        when(clientService.saveClient(any())).thenReturn(client);

        mockMvc.perform(post("/index/clientForm"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/loanForm"));

    }


}