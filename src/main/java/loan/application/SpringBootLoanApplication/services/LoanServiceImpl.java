package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.mapper.LoanMapper;
import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.exceptions.CannotCreateLoanException;
import loan.application.SpringBootLoanApplication.exceptions.LoanNotFoundException;
import loan.application.SpringBootLoanApplication.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public LoanServiceImpl(LoanRepository loanRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
    }

    @Override
    public Loan saveLoan(Loan loan) throws CannotCreateLoanException {
        int hourOfGettingLoan = getCurrentHour();

        if (canCreateLoan(hourOfGettingLoan)) {
            return this.loanRepository.save(loan);
        } else {
            throw new CannotCreateLoanException();
        }
    }

    @Override
    public Loan updateLoan(Loan loan) throws CannotCreateLoanException {

        if (!loan.isLoanDelay()) {
            delayPayDate(loan);
            return this.loanRepository.save(loan);
        } else {
            throw new CannotCreateLoanException();
        }
    }

    @Override
    public Loan getLoanById(Long id) throws LoanNotFoundException {

        Optional<Loan> loanOptional = loanRepository.findById(id);

        if (!loanOptional.isPresent()) {
            throw new LoanNotFoundException(id);
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
    public LoanDTO findLoanDtoById(Long id) {
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
        LoanDTO returnDTO = loanMapper.loanToLoanDTO(savedLoan);

        returnDTO.setId(savedLoan.getId());
        return returnDTO;

    }

    private boolean canCreateLoan(int hourOfGettingLoan) {
        return hourOfGettingLoan < 24 && hourOfGettingLoan > 6;
    }

    private int getCurrentHour() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    private void delayPayDate(Loan loan) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(loan.getDueDate());

        cal.add(Calendar.DATE, 14);
        Date modifiedDate = cal.getTime();

        loan.setDueDate(modifiedDate);
        loan.setLoanDelay(true);
    }
}
