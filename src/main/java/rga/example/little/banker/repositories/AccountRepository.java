package rga.example.little.banker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rga.example.little.banker.entities.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
