package loan.application.SpringBootLoanApplication.domain;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "loan_id")
    private Long id;

    @NotNull
    @Min(1)
    @Max(30000)
    @Column(name = "amount")
    private int amount;

    @NotNull
    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "loan_delay")
    private boolean isLoanDelay;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Loan(Long id, int amount, Date dueDate, boolean isLoanDelay, Client client) {
        this.id = id;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isLoanDelay = isLoanDelay;
        this.client = client;
    }

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
