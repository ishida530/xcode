package com.xcode.currencies.repository;

import com.xcode.currencies.entity.CurrencyRequestInfoEntity;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableAutoConfiguration
public interface CurrenciesRepository extends JpaRepository<CurrencyRequestInfoEntity, Long> {


}
