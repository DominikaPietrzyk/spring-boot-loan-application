package loan.application.SpringBootLoanApplication.api.model;

import lombok.Data;

@Data
public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private int personIdNumber;
    private String city;
    private String street;
    private int streetNumber;

}
