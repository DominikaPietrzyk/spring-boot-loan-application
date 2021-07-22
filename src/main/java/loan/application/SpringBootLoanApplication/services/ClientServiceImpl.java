package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

   private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {

        Optional<Client> clientOptional = clientRepository.findById(id);

        if (!clientOptional.isPresent()) {
            throw new RuntimeException("Client Not Found!");
        }

        return clientOptional.get();
    }
}
