package com.multBancapp.apimultbanc.repositories;

import com.multBancapp.apimultbanc.entities.AccountEntity;
import com.multBancapp.apimultbanc.models.projections.ListTransferQuantitiesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

      @Query(nativeQuery = true, value = """
              Select a.agency, a.balance, a.holder_id, a.id, a.number_account, a.performace, a.rate,
                   a.type_account_id
              from tb_conta a 
              WHERE a.holder_id = :holder
       """)
      AccountEntity findByAccountUser(Long holder);

      @Query(value = "Select a from AccountEntity a Where a.number = :number")
      AccountEntity findByNumberAccount(Integer number);

      @Query(nativeQuery = true, value = """
                 select c.number_account as numAccount, u.email, count(c.holder_id) as qte from tb_conta c
                                             join FETCH tb_transferencia t on (t.source_account_id = c.id)
                                             join FETCH tb_usuarios u on (u.id = c.holder_id)
                  group by u.email, c.number_account
      """)
      List<ListTransferQuantitiesProjection> listTranferAccounts();

}
