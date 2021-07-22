package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.mapper.LoanMapper;
import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public LoanServiceImpl(LoanRepository loanRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
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

    @Override
    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(loanMapper::loanToLoanDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDTO findLoanById(Long id) {
        return loanRepository
                .findById(id)
                .map(loanMapper::loanToLoanDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public LoanDTO createNewLoan(LoanDTO loanDTO) {
        return saveAndReturnDTO(loanMapper.loanDtoToLoan(loanDTO));
    }

    @Override
    public LoanDTO saveLoanByDTO(Long id, LoanDTO loanDTO) {
        Loan loan = loanMapper.loanDtoToLoan(loanDTO);
        loan.setId(id);

        return saveAndReturnDTO(loan);
    }

    @Override
    public void deleteLoanById(Long id) {
        loanRepository.deleteById(id);
    }

    private LoanDTO saveAndReturnDTO(Loan loan) {
        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.loanToLoanDTO(savedLoan);
    }
}
