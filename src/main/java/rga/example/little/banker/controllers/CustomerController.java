package rga.example.little.banker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rga.example.little.banker.entities.Customer;
import rga.example.little.banker.exceptions.AlreadyExistsException;
import rga.example.little.banker.exceptions.NotFoundException;
import rga.example.little.banker.services.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService service;

    /*
    Create a new customer
     */
    @PostMapping("/create")
    public ResponseEntity<Void> create (@RequestBody Customer customer){
        service.create(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    Update an existent customer
     */
    @PutMapping("/modify")
    public ResponseEntity<Void> modify (@RequestBody Customer customer){
        service.modify(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    Remove customer by id
    */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeById(@PathVariable UUID id){
        service.removeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    Find customer by id
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Customer> findById(UUID id){
        return ResponseEntity.ok(service.findById(id));
    }

    /*
    Find all customers
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(new ArrayList<>(service.findAll()));
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
