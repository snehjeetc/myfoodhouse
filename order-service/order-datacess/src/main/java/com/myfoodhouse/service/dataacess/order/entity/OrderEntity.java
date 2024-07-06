package com.myfoodhouse.service.dataacess.order.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.myfoodhouse.sys.domain.valueobjects.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderEntity {
    
    @Id
    private UUID id; 
    private UUID customerId; 
    private UUID restaurantId; 
    private UUID trackingId; 
    private BigDecimal price; 
    
    //create EnumType in postgres as well
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String failureMessages; 
    
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderAddressEntity address; 

    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items; 
}
