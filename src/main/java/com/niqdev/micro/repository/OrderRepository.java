package com.niqdev.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niqdev.micro.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
