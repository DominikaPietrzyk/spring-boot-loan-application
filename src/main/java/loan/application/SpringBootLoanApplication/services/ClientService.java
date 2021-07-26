package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.domain.Client;

import java.util.List;

public interface ClientService {

    Client saveClient(Client client);

    Client getClientById(Long id);



    List<ClientDTO> getAllClient();

    ClientDTO findClientById(Long id);

    ClientDTO createNewClient(ClientDTO clientDTO);

    ClientDTO saveClientByDTO(Long id, ClientDTO clientDTO);

    void deleteClientById(Long id);


}
