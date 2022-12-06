package rga.example.little.banker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rga.example.little.banker.entities.Transfer;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {

    List<Transfer> findByAmount(Double amount);

    List<Transfer> findByMessage(String message);

    @Query(
            value = "SELECT * FROM transfer t WHERE t.debtor_iban = ?1 OR t.creditor_iban = ?1 ",
            nativeQuery = true
    )
    List<Transfer> findByIban(String iban);

}
