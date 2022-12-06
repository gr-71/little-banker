package rga.example.little.banker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rga.example.little.banker.entities.Account;
import rga.example.little.banker.exceptions.AlreadyExistsException;
import rga.example.little.banker.exceptions.NotFoundException;
import rga.example.little.banker.services.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private final AccountService service;

    /*
    Create a new account
     */
    @PostMapping("/create")
    public ResponseEntity<Void> create (@RequestBody Account account){
        service.create(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    Modify an existent account
     */
    @PutMapping("/modify")
    public ResponseEntity<Void> modify (@RequestBody Account account){
        service.modify(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    Remove an existent account by IBAN
     */
    @DeleteMapping("/remove/{iban}")
    public ResponseEntity<Void> removeById(@PathVariable String iban){
        service.removeById(iban);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    Get account summary by IBAN
     */
    @GetMapping(value = "/summary/{iban}", produces = "application/json")
    public ResponseEntity<Account> viewSummaryByIban(String iban){
        return ResponseEntity.ok(service.viewSummaryByIban(iban));
    }

    /*
    Get account balance by IBAN
     */
    @GetMapping(value = "/balance/{iban}")
    public ResponseEntity<Double> viewBalanceByIban(String iban){
        return ResponseEntity.ok(service.viewBalanceByIban(iban));
    }

    /*
    Exception handlers
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Void> handleBadRequestException() {
        return ResponseEntity.badRequest().build();
    }
}
