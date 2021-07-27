package loan.application.SpringBootLoanApplication.exceptions;

public class LoanNotFoundException extends DomainException{
    public LoanNotFoundException(Long id) {
        setCode("load_with_id_" + id + "not_found");
    }
}
