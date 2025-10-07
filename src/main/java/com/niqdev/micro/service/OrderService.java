package com.niqdev.micro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.niqdev.micro.entity.Order;
import com.niqdev.micro.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;

	public Order placeOrder(Order order) {
		Boolean inStock = webClientBuilder.build()
				.get()
				.uri("http://inventory-service/inventory/check/" + order.getProductCode())
				.retrieve()
				.bodyToMono(Boolean.class)
				.block();

		if (Boolean.TRUE.equals(inStock)) {
			return orderRepository.save(order);
		}
		throw new RuntimeException("Product out of stock");
	}
	
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
