package loan.application.SpringBootLoanApplication.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Loan> loanSet = new HashSet<>();

    @NotNull
    @NotEmpty
    @Size(min=2, max=30)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min=2, max=30)
    @Column(name = "last_name")
    private String lastName;

    public Client() {
    }

    public Client(Long id, Set<Loan> loanSet, String firstName, String lastName) {
        this.id = id;
        this.loanSet = loanSet;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Loan> getLoanSet() {
        return loanSet;
    }

    public void setLoanSet(Set<Loan> loanSet) {
        this.loanSet = loanSet;
    }
}
