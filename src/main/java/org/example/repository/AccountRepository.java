package org.example.repository;

import org.example.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Modifying
    @Query("UPDATE Account SET balance = ?2 WHERE id = ?1")
    void updateBalanceOnAccountWithID(Long id,BigDecimal balance);

}
