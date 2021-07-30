package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.mapper.LoanMapper;
import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.domain.Loan;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class AcceptanceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    LoanService loanService;

    LoanMapper loanMapper = LoanMapper.INSTANCE;

    @Test
    public void should_create_client_data() {

        ClientDTO client = new ClientDTO();
        client.setFirstName("Anna");
        client.setLastName("Nowak");

        ClientDTO createdClient = clientService.createNewClient(client);

        assertThat(createdClient)
                .returns("Anna", ClientDTO::getFirstName)
                .returns("Nowak", ClientDTO::getLastName);
    }

    @Test
    public void should_save_loan_data_and_should_allow_you_to_take_loans_between_6_and_24() throws ParseException {
        Loan loan = new Loan();
        loan.setAmount(25000);
        loan.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-31"));

        Loan savedLoan = loanService.saveLoan(loan);

        assertThat(savedLoan)
                .returns(25000, Loan::getAmount)
                .returns(new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-31"), Loan::getDueDate);
    }

    @Test
    public void should_find_loan_by_id_and_delay_loan_repayment_date_if_loan_has_not_been_delay_before() throws ParseException {

        Loan loan = new Loan();
        loan.setAmount(25000);
        loan.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-31"));
        loan.setLoanDelay(false);

        Loan savedLoan = loanService.saveLoan(loan);

        LoanDTO foundLoan = loanService.findLoanDtoById(savedLoan.getId());

        Loan updatedLoan = loanService.updateLoan(loanMapper.loanDtoToLoan(foundLoan));

        assertThat(updatedLoan)
                .returns(3L, Loan::getId)
                .returns(25000, Loan::getAmount)
                .returns(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-14"), Loan::getDueDate)
                .returns(true, Loan::isLoanDelay);
    }
}