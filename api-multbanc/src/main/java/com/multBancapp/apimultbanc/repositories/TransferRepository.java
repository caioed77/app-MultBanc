package com.multBancapp.apimultbanc.repositories;

import com.multBancapp.apimultbanc.entities.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

}
