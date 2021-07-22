package loan.application.SpringBootLoanApplication.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanListDTO {

    List<LoanDTO> loanDTOList;
}
