package loan.application.SpringBootLoanApplication.api.v1.model;

import java.util.List;

public class LoanListDTO {

    List<LoanDTO> loanDTOList;

    public LoanListDTO(List<LoanDTO> loanDTOList) {
        this.loanDTOList = loanDTOList;
    }

    public LoanListDTO() {
    }

    public List<LoanDTO> getLoanDTOList() {
        return loanDTOList;
    }

    public void setLoanDTOList(List<LoanDTO> loanDTOList) {
        this.loanDTOList = loanDTOList;
    }
}
