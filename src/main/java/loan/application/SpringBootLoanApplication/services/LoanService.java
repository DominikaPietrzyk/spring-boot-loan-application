package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.exceptions.CannotCreateLoanException;
import loan.application.SpringBootLoanApplication.exceptions.LoanNotFoundException;

import java.util.List;

public interface LoanService {

    Loan getLoanById(Long id) throws LoanNotFoundException;

    Loan saveLoan(Loan loan) throws CannotCreateLoanException;


    List<LoanDTO> getAllLoans();

    LoanDTO findLoanDtoById(Long id);

    LoanDTO createNewLoan(LoanDTO LoanDTO);

    LoanDTO saveLoanByDTO(Long id, LoanDTO loanDTO);

    void deleteLoanById(Long id);
}
