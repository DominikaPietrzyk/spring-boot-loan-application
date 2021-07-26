package loan.application.SpringBootLoanApplication.mvc.controllers;

import loan.application.SpringBootLoanApplication.services.ClientService;

class IndexControllerTest {

    private final ClientService clientService;

    IndexControllerTest(ClientService clientService) {
        this.clientService = clientService;
    }

}