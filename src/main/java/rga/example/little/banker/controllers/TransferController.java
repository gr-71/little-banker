package rga.example.little.banker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rga.example.little.banker.entities.Transfer;
import rga.example.little.banker.exceptions.NotEnoughMoneyException;
import rga.example.little.banker.exceptions.NotFoundException;
import rga.example.little.banker.services.TransferService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private final TransferService service;

    /*
    Make money transfer
     */
    @PostMapping("/")
    public ResponseEntity<Void> moneyTransfer(@RequestBody Transfer transfer){
        service.moneyTransfer(transfer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    Get transfers' list by amount of money
     */
    @GetMapping(value = "/{amount}", produces = "application/json")
    public ResponseEntity<List<Transfer>> findByAmount(@PathVariable Double amount){
        return ResponseEntity.ok(new ArrayList<>(service.findByAmount(amount)));
    }

    /*
    Get transfers' list by IBAN
     */
    @GetMapping(value = "/{iban}", produces = "application/json")
    public ResponseEntity<List<Transfer>> findByIban(@PathVariable String iban){
        return ResponseEntity.ok(new ArrayList<>(service.findByIban(iban)));
    }

    /*
    Get transfers' list by message
     */
    @GetMapping(value = "/{message}", produces = "application/json")
    public ResponseEntity<List<Transfer>> findByAmountMessage(@PathVariable String message){
        return ResponseEntity.ok(new ArrayList<>(service.findByMessage(message)));
    }

    /*
    Exception handlers
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<Void> handleBadRequestException() {
        return ResponseEntity.badRequest().build();
    }
}
