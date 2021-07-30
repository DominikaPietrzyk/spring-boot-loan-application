package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.mapper.ClientMapper;
import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @Mock
    ClientRepository clientRepository;

    ClientMapper clientMapper = ClientMapper.INSTANCE;

    ClientService clientService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        clientService = new ClientServiceImpl(clientRepository, clientMapper);
    }

    @Test
    public void getAllClients() throws Exception {

        Client client1 = new Client();
        client1.setId(1L);
        client1.setFirstName("Maria");
        client1.setLastName("Nowak");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setFirstName("Jan");
        client2.setLastName("Kowalski");

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<ClientDTO> clientDTOList = clientService.getAllClient();

        assertEquals(2, clientDTOList.size());
    }

    @Test
    public void getClientById() throws Exception {

        Client client1 = new Client();
        client1.setId(1L);
        client1.setFirstName("Maria");
        client1.setLastName("Nowak");

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client1));

         clientService.findClientById(1L);

        assertEquals(1L, client1.getId());
        assertEquals("Maria", client1.getFirstName());
        assertEquals("Nowak", client1.getLastName());
    }

    @Test
    public void deleteClientById() throws Exception {

        clientRepository.deleteById(1L);
        verify(clientRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void createNewClientDTO() throws Exception {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("Jan");
        clientDTO.setLastName("Nowak");

        Client savedClient = new Client();
        savedClient.setFirstName(clientDTO.getFirstName());
        savedClient.setLastName(clientDTO.getLastName());
        savedClient.setId(1L);

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        ClientDTO savedDTO = clientService.createNewClient(clientDTO);

        assertEquals(clientDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(clientDTO.getLastName(), savedDTO.getLastName());
    }

    @Test
    public void saveClientByDTO() throws Exception {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("Jan");
        clientDTO.setLastName("Nowak");

        Client savedClient = new Client();
        savedClient.setFirstName(clientDTO.getFirstName());
        savedClient.setLastName(clientDTO.getLastName());
        savedClient.setId(1L);

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        ClientDTO savedDTO = clientService.updateClientByDTO(1L, clientDTO);

        assertEquals(clientDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(clientDTO.getLastName(), savedDTO.getLastName());
    }


}