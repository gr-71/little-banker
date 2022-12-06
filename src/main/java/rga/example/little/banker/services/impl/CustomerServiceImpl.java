package rga.example.little.banker.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rga.example.little.banker.entities.Customer;
import rga.example.little.banker.exceptions.NotFoundException;
import rga.example.little.banker.repositories.CustomerRepository;
import rga.example.little.banker.services.CustomerService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @NonNull
    private CustomerRepository repository;

    @Override
    public void create(Customer customer) {
        if(repository.existsById(customer.getId())){
            log.warn("Customer with id: {} already exists in the system!", customer.getId());
        }
        repository.save(customer);
    }

    @Override
    public void modify(Customer customer) {
        if(!repository.existsById(customer.getId())){
            log.warn("There is no customer with id: {} in the system!", customer.getId());
        }
        repository.save(customer);
    }

    @Override
    @Transactional
    public void removeById(UUID id) {
        if(!repository.existsById(id)){
            log.warn("There is no customer with id: {} to remove!", id);
            throw new NotFoundException();
        }
        repository.deleteById(id);
    }

    @Override
    public Customer findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn("There is no customer with id: {} in the system!", id);
            throw new NotFoundException();
        });
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }
}
