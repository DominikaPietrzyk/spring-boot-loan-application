package loan.application.SpringBootLoanApplication.api.v1.model;

import java.util.Date;

public class LoanDTO {

    private Long id;
    private int Amount;
    private Date dueDate;
    private boolean isLoanDelay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isLoanDelay() {
        return isLoanDelay;
    }

    public void setLoanDelay(boolean loanDelay) {
        isLoanDelay = loanDelay;
    }
}
