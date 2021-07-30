package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.mapper.ClientMapper;
import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> getAllClient() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::clientToClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO findClientById(Long id) {
        return clientRepository
                .findById(id)
                .map(clientMapper::clientToClientDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ClientDTO createNewClient(ClientDTO clientDTO) {
        return saveAndReturnDTO(clientMapper.clientDtoToClient(clientDTO));
    }

    @Override
    public ClientDTO updateClientByDTO(Long id, ClientDTO clientDTO) {
        Client client = clientMapper.clientDtoToClient(clientDTO);
        client.setId(id);

        return saveAndReturnDTO(client);
    }

    private ClientDTO saveAndReturnDTO(Client client) {

        Client savedClient = clientRepository.save(client);
        return clientMapper.clientToClientDTO(savedClient);
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
