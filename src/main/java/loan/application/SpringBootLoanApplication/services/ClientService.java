package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.domain.Client;

public interface ClientService {
    Client saveClient(Client client);
    Client getClientById(Long id);

}
