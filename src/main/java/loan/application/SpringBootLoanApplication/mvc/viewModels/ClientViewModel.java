package loan.application.SpringBootLoanApplication.mvc.viewModels;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ClientViewModel {

    @NotNull
    @NotEmpty
    @Size(min=2, max=30)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min=2, max=30)
    private String lastName;

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
}
