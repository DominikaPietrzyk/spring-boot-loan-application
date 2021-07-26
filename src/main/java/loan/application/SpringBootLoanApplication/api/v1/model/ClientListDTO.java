package loan.application.SpringBootLoanApplication.api.v1.model;

import java.util.List;


public class ClientListDTO {
    List<ClientDTO> clientDTOList;

    public ClientListDTO(List<ClientDTO> clientDTOList) {
        this.clientDTOList = clientDTOList;
    }

    public ClientListDTO() {
    }

    public List<ClientDTO> getClientDTOList() {
        return clientDTOList;
    }

    public void setClientDTOList(List<ClientDTO> clientDTOList) {
        this.clientDTOList = clientDTOList;
    }
}
