package sg.edu.nus.iss.day24_lecture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day24_lecture.payloads.TransferRequest;
import sg.edu.nus.iss.day24_lecture.services.BankAccountService;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    
    @Autowired
    BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<Boolean> transferMoney(@RequestBody TransferRequest transferRequest) {
        
        Boolean bTransferred = false;

        bTransferred = bankAccountService.transferMoney(transferRequest.getAccountFrom(), transferRequest.getAccountTo(), transferRequest.getAmount());

        if (bTransferred) {
            return new ResponseEntity<Boolean>(bTransferred, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(bTransferred, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
