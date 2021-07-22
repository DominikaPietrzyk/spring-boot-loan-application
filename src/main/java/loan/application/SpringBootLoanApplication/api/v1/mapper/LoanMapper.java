package loan.application.SpringBootLoanApplication.api.v1.mapper;

import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.domain.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    LoanDTO loanToLoanDTO(Loan loan);

    Loan loanDtoToLoan(LoanDTO loanDTO);

}
