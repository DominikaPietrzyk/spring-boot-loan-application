package loan.application.SpringBootLoanApplication.bootstrap;

import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.repositories.ClientRepository;
import loan.application.SpringBootLoanApplication.repositories.LoanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class Bootstrap implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;

    public Bootstrap(ClientRepository clientRepository, LoanRepository loanRepository) {
        this.clientRepository = clientRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Client client = new Client();

        client.setId(1L);
        client.setFirstName("Maria");
        client.setLastName("Kowalska");

        clientRepository.save(client);

        Client client2 = new Client();

        client2.setId(2L);
        client2.setFirstName("Jan");
        client2.setLastName("Nowak");

        clientRepository.save(client2);

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-09-24"));
        loan.setAmount(2343);
        loan.setClient(client);
        loan.setLoanExtension(true);

        loanRepository.save(loan);

        Loan loan2 = new Loan();
        loan2.setId(2L);
        loan2.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-24"));
        loan2.setAmount(9999);
        loan2.setClient(client2);
        loan.setLoanExtension(false);

        loanRepository.save(loan2);
    }
}
