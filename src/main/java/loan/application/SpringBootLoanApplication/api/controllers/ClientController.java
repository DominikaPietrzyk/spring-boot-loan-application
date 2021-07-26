package loan.application.SpringBootLoanApplication.api.controllers;

import io.swagger.annotations.Api;
import loan.application.SpringBootLoanApplication.api.v1.model.ClientDTO;
import loan.application.SpringBootLoanApplication.api.v1.model.ClientListDTO;
import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ClientController.BASE_URL)
@RestController
public class ClientController {

     public static final String BASE_URL = "/api/v1/client";

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id) {
        return new ResponseEntity<ClientDTO>
                (clientService.findClientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createNewClient(@RequestBody ClientDTO clientDTO){
        return new ResponseEntity<ClientDTO>
                (clientService.createNewClient(clientDTO),HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        return new ResponseEntity<ClientDTO>
                (clientService.saveClientByDTO(id,clientDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ClientListDTO> getListOfClient(){
        return new ResponseEntity<ClientListDTO>
                (new ClientListDTO(clientService.getAllClient()),HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteClientById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
