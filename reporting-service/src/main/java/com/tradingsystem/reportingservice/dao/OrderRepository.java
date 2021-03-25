package com.tradingsystem.reportingservice.dao;
import com.tradingsystem.reportingservice.dto.TradeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<TradeOrder, Long> {

    Optional<TradeOrder> findById(Long aLong);

    List<TradeOrder> findAll();

//    <S extends TradeOrder> S save(S s);
}
