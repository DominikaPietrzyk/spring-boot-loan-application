package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.domain.Loan;

public interface LoanService {

    Loan getLoanById(Long id);
    Loan saveLoan(Loan loan);
}
