package com.example.gestionlocauxcommerciauxmvc.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long>{
        List<UserOrder> findAllByUserId(Long userId);
}
