package loan.application.SpringBootLoanApplication.api.controllers;

import loan.application.SpringBootLoanApplication.api.v1.model.LoanDTO;
import loan.application.SpringBootLoanApplication.api.v1.model.LoanListDTO;
import loan.application.SpringBootLoanApplication.services.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(LoanController.BASE_URL)
@RestController
public class LoanController {

    public static final String BASE_URL = "/api/v1/loan";

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<LoanDTO> findLoanById(@PathVariable Long id) {
        return new ResponseEntity<LoanDTO>
                (loanService.findLoanById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createNewLoan(@RequestBody LoanDTO loanDTO){
        return new ResponseEntity<LoanDTO>
                (loanService.createNewLoan(loanDTO),HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long id, @RequestBody LoanDTO loanDTO){
        return new ResponseEntity<LoanDTO>
                (loanService.saveLoanByDTO(id,loanDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<LoanListDTO> getListOfLoans(){
        return new ResponseEntity<LoanListDTO>
                (new LoanListDTO(loanService.getAllLoans()),HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id){
        loanService.deleteLoanById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
