package loan.application.SpringBootLoanApplication.mvc.viewModels;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

public class LoanViewModel {

    @NotNull
    @Min(1)
    @Max(10000)
    private int Amount;

    @NotNull
    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    public LoanViewModel() {
    }

    public LoanViewModel(int amount, Date dueDate) {
        Amount = amount;
        this.dueDate = dueDate;
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
}
