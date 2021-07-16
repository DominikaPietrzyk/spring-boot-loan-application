package loan.application.SpringBootLoanApplication.api.mapper;

import loan.application.SpringBootLoanApplication.api.model.LoanDTO;
import loan.application.SpringBootLoanApplication.domain.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    LoanDTO loanToLoanDTO(Loan loan);

    Loan loanDtoToLoan(LoanDTO loanDTO);

}
