package com.niqdev.micro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	  @Id 
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String productCode;
	  private Integer quantity;
}
