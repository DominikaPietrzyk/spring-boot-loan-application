package loan.application.SpringBootLoanApplication.bootstrap;

import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final ClientRepository clientRepository;

    public Bootstrap(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Client client = new Client();

        client.setId(1L);
        client.setFirstName("Maria");
        client.setLastName("Sianowska");

        clientRepository.save(client);

        Client client2 = new Client();

        client2.setId(2L);
        client2.setFirstName("Maria2");
        client2.setLastName("Sianowska2");

        clientRepository.save(client2);
        System.out.println("Count clients: " + clientRepository.count());
    }
}
