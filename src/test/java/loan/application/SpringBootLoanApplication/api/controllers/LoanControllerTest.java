package loan.application.SpringBootLoanApplication.api.controllers;


import loan.application.SpringBootLoanApplication.api.RestResponseEntityExceptionHandler;
import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.services.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class LoanControllerTest extends AbstractRestControllerTest{

    @Mock
    LoanService loanService;

    @InjectMocks
    LoanController loanController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(loanController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListLoans() throws Exception {

        LoanDTO loan1 = new LoanDTO();
        loan1.setId(1L);
        loan1.setAmount(2341);
        loan1.setDueDate(new SimpleDateFormat("yyyy/MM/dd").parse("2021/10/24"));
        loan1.setLoanDelay(true);

        LoanDTO loan2 = new LoanDTO();
        loan2.setId(2L);
        loan2.setAmount(4350);
        loan2.setDueDate(new SimpleDateFormat("yyyy/MM/dd").parse("2021/09/24"));
        loan2.setLoanDelay(false);

        List<LoanDTO> loanDTOList = Arrays.asList(loan1, loan2);

        when(loanService.getAllLoans()).thenReturn(loanDTOList);

        mockMvc.perform(get(LoanController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loanDTOList", hasSize(2)));
    }

    @Test
    public void testFindLoanById() throws Exception {
        LoanDTO loan1 = new LoanDTO();
        loan1.setId(1L);
        loan1.setAmount(4350);
        loan1.setDueDate(new SimpleDateFormat("yyyy/MM/dd").parse("2021/09/24"));
        loan1.setLoanDelay(false);

        when(loanService.findLoanDtoById(anyLong())).thenReturn(loan1);

        mockMvc.perform(get(LoanController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", equalTo(4350)));
    }

    @Test
    public void testDeleteLoan() throws Exception {

        mockMvc.perform(delete(LoanController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(loanService).deleteLoanById(anyLong());
    }

    @Test
    public void testUpdateLoan() throws Exception {
        LoanDTO loan1 = new LoanDTO();
        loan1.setId(1L);
        loan1.setAmount(4350);
        loan1.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-09-24"));
        loan1.setLoanDelay(true);

        when(loanService.saveLoanByDTO(anyLong(), any(LoanDTO.class))).thenReturn(loan1);

        mockMvc.perform(put(LoanController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loan1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.amount", equalTo(4350)))
                .andExpect(jsonPath("$.loanDelay",equalTo(true)));
    }

    @Test
    public void createNewLoan() throws Exception {
        LoanDTO loan1 = new LoanDTO();
        loan1.setId(1L);
        loan1.setAmount(4350);
        loan1.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-09-24"));
        loan1.setLoanDelay(true);

        when(loanService.createNewLoan(any(LoanDTO.class))).thenReturn(loan1);

        mockMvc.perform(post(LoanController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loan1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.amount", equalTo(4350)))
                .andExpect(jsonPath("$.loanDelay",equalTo(true)));
    }
}