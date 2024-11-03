package com.xcode.currencies.repository;

import com.xcode.currencies.entity.CurrencyRequestInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


public interface CurrenicesRepository extends JpaRepository<CurrencyRequestInfoEntity, Long> {


}
