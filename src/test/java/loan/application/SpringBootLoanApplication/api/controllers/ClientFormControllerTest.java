package loan.application.SpringBootLoanApplication.api.controllers;

import loan.application.SpringBootLoanApplication.api.RestResponseEntityExceptionHandler;
import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientFormControllerTest extends AbstractRestControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    ClientController clientController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListClients() throws Exception {

        ClientDTO client1 = new ClientDTO();
        client1.setId(1L);
        client1.setFirstName("Jan");
        client1.setLastName("Kowalski");

        ClientDTO client2 = new ClientDTO();
        client1.setId(2L);
        client2.setFirstName("Adam");
        client2.setLastName("Nowak");

        List<ClientDTO> clientDTOList = Arrays.asList(client1, client2);

        when(clientService.getAllClient()).thenReturn(clientDTOList);

        mockMvc.perform(get(ClientController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientDTOList", hasSize(2)));
    }


    @Test
    public void testFindClientById() throws Exception {
        ClientDTO client1 = new ClientDTO();
        client1.setId(1L);
        client1.setFirstName("Jan");
        client1.setLastName("Kowalski");

        when(clientService.findClientById(anyLong())).thenReturn(client1);

        mockMvc.perform(get(ClientController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Jan")));
    }

    @Test
    public void testDeleteClient() throws Exception {

        mockMvc.perform(delete(ClientController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(clientService).deleteClientById(anyLong());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        ClientDTO client1 = new ClientDTO();
        client1.setFirstName("Jan");
        client1.setLastName("Kowalski");

        ClientDTO returnDTO = new ClientDTO();
        returnDTO.setFirstName(client1.getFirstName());
        returnDTO.setLastName(client1.getLastName());

        when(clientService.updateClientByDTO(anyLong(), any(ClientDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(ClientController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Jan")))
                .andExpect(jsonPath("$.lastName", equalTo("Kowalski")));
    }


    @Test
    public void testCreteClient() throws Exception {

        ClientDTO client = new ClientDTO();
        client.setId(1L);
        client.setFirstName("Jan");
        client.setLastName("Kowalski");

        when(clientService.createNewClient(any(ClientDTO.class))).thenReturn(client);

        mockMvc.perform(post(ClientController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", equalTo("Jan")))
                .andExpect(jsonPath("$.lastName", equalTo("Kowalski")));
    }
}