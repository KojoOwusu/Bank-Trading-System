package com.tradingsystem.reportingservice.dao;
import com.tradingsystem.reportingservice.dto.TradeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<TradeOrder, Long> {

    Optional<TradeOrder> findById(Long aLong);

    List<TradeOrder> findAll();

    @Query(value = "SELECT orderid FROM TradeOrder")
    List<String> findallIDs();

    @Modifying
    @Query("update TradeOrder t set t.orderstatus = :orderstatus where t.orderid=:orderid")
    int updateOrderStatus(@Param("orderstatus") String status, @Param("orderid")String orderid);

    @Modifying
    @Query("update TradeOrder t set t.executions = :executions where t.orderid=:orderid")
    int updateExecutions(@Param("executions") int executions, @Param("orderid") String orderid);

//    <S extends TradeOrder> S save(S s);
}
