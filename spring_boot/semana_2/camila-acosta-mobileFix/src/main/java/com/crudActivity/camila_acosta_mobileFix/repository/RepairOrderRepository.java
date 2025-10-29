package com.crudActivity.camila_acosta_mobileFix.repository;

import com.crudActivity.camila_acosta_mobileFix.model.RepairOrder;
import com.crudActivity.camila_acosta_mobileFix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

    List<RepairOrder> findByCustomer(User customer);
    List<RepairOrder> findByAssignedTech(User tech);

}
