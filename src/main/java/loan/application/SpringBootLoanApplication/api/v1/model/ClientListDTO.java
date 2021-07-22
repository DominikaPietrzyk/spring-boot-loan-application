package loan.application.SpringBootLoanApplication.api.v1.model;

import lombok.Data;

import java.util.List;


@Data
public class ClientListDTO {
    List<ClientDTO> clientDTOList;
}
