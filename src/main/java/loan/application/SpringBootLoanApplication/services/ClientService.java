package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.domain.Client;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClient();

    ClientDTO findClientById(Long id);

    ClientDTO createNewClient(ClientDTO clientDTO);

    ClientDTO updateClientByDTO(Long id, ClientDTO clientDTO);

    void deleteClientById(Long id);
}
