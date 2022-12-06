package rga.example.little.banker.services;

import rga.example.little.banker.entities.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    void create(Customer customer);

    void modify(Customer customer);

    void removeById (UUID id);

    Customer findById (UUID id);

    List<Customer> findAll();
}
