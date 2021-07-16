package loan.application.SpringBootLoanApplication.api.mapper;

import loan.application.SpringBootLoanApplication.api.model.ClientDTO;
import loan.application.SpringBootLoanApplication.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO clientToClientDTO(Client client);

    Client clientDtoToClient(ClientDTO clientDTO);
}
