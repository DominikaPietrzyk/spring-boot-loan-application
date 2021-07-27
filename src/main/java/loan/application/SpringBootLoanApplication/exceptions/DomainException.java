package loan.application.SpringBootLoanApplication.exceptions;

public class DomainException extends Exception {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
