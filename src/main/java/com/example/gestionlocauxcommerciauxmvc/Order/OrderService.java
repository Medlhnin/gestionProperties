package com.example.gestionlocauxcommerciauxmvc.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(UserOrder userOrder) {
        orderRepository.save(userOrder);
    }

    public List<UserOrder> getOrders() {
        return orderRepository.findAll();
    }

    public UserOrder getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalStateException("UserOrder with id " + id + " not found"));
    }

    public List<UserOrder> getOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

}
