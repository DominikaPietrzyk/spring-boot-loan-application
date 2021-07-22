package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan saveLoan(Loan loan) {
        return this.loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(Long id) {

        Optional<Loan> loanOptional = loanRepository.findById(id);

        if (!loanOptional.isPresent()) {
            throw new RuntimeException("Loan Not Found!");
        }

        return loanOptional.get();
    }



}
