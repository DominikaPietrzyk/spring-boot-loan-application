package loan.application.SpringBootLoanApplication.exceptions;

public class CannotCreateLoanException extends DomainException {
    public CannotCreateLoanException() {
        setCode("cannot_create_loan_between_24_and_6");
    }
}
