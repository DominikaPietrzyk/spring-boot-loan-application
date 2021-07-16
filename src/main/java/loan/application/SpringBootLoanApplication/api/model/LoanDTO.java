package loan.application.SpringBootLoanApplication.api.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanDTO {

    private Long id;
    private int Amount;
    private LocalDate requestDate;
    private LocalDate dueDate;
}
