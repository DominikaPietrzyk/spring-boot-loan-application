package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.domain.Loan;

import java.util.List;

public interface LoanService {

    Loan getLoanById(Long id);

    Loan saveLoan(Loan loan);


    List<LoanDTO> getAllLoans();

    LoanDTO findLoanById(Long id);

    LoanDTO createNewLoan(LoanDTO LoanDTO);

    LoanDTO saveLoanByDTO(Long id, LoanDTO loanDTO);

    void deleteLoanById(Long id);
}
