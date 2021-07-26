package loan.application.SpringBootLoanApplication.services;

import loan.application.SpringBootLoanApplication.api.v1.mapper.LoanMapper;
import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.domain.Client;
import loan.application.SpringBootLoanApplication.domain.Loan;
import loan.application.SpringBootLoanApplication.repositories.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class LoanServiceImplTest {

    @Mock
    LoanRepository loanRepository;

    LoanMapper loanMapper = LoanMapper.INSTANCE;

    LoanService loanService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        loanService = new LoanServiceImpl(loanRepository, loanMapper);
    }

    @Test
    public void getAllLoans() throws Exception {

        Loan loan1 = new Loan();
        loan1.setId(1L);
        loan1.setAmount(2341);
        loan1.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-24"));
        loan1.setLoanExtension(true);

        Loan loan2 = new Loan();
        loan2.setId(2L);
        loan2.setAmount(4350);
        loan2.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-09-2 4"));
        loan2.setLoanExtension(false);

        Loan loan3 = new Loan();
        loan3.setId(3L);
        loan3.setAmount(3500);
        loan3.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-12"));
        loan3.setLoanExtension(true);

        when(loanRepository.findAll()).thenReturn(Arrays.asList(loan1, loan2, loan3));

        List<LoanDTO> loanDTOList = loanService.getAllLoans();

        assertEquals(3, loanDTOList.size());
    }

    @Test
    public void getLonaById() throws Exception {

        Loan loan1 = new Loan();
        loan1.setId(1L);
        loan1.setAmount(2341);
        loan1.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-24"));
        loan1.setLoanExtension(true);

        when(loanRepository.findById(anyLong())).thenReturn(Optional.ofNullable(loan1));

        LoanDTO loanDTO = loanService.findLoanById(1L);

        assertEquals(1L,loan1.getId());
        assertEquals(2341, loan1.getAmount());
    }

    @Test
    public void deleteLoanById() throws Exception {

        loanRepository.deleteById(1L);
        verify(loanRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void createNewLoanDTO() throws Exception {

        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(1L);
        loanDTO.setAmount(6789);
        loanDTO.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-14"));
        loanDTO.setLoanExtension(false);

        Loan savedLoan = new Loan();
        savedLoan.setAmount(loanDTO.getAmount());
        savedLoan.setDueDate(loanDTO.getDueDate());
        savedLoan.setLoanExtension(loanDTO.isLoanExtension());
        savedLoan.setId(loanDTO.getId());

        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);

        LoanDTO savedDTO = loanService.createNewLoan(loanDTO);

        assertEquals(1L,savedDTO.getId());
        assertEquals(6789, savedDTO.getAmount());
        assertEquals(false,savedDTO.isLoanExtension());
    }

    @Test
    public void saveLoanByDTO() throws Exception {

        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(1L);
        loanDTO.setAmount(6789);
        loanDTO.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-14"));
        loanDTO.setLoanExtension(false);

        Loan savedLoan = new Loan();
        savedLoan.setAmount(loanDTO.getAmount());
        savedLoan.setDueDate(loanDTO.getDueDate());
        savedLoan.setLoanExtension(loanDTO.isLoanExtension());
        savedLoan.setId(loanDTO.getId());;

        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);

        LoanDTO savedDTO = loanService.saveLoanByDTO(1L, loanDTO);

        assertEquals(1L,savedDTO.getId());
        assertEquals(6789, savedDTO.getAmount());
        assertEquals(false,savedDTO.isLoanExtension());
    }


}