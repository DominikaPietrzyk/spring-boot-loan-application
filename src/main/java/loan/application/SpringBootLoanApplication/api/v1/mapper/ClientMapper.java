package loan.application.SpringBootLoanApplication.api.v1.mapper;

import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.domain.Client;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE  = Mappers.getMapper(ClientMapper.class);

    ClientDTO clientToClientDTO(Client client);

    Client clientDtoToClient(ClientDTO clientDTO);
}
