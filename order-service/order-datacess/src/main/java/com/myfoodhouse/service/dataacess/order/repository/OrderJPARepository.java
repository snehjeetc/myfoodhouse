package com.myfoodhouse.service.dataacess.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfoodhouse.service.dataacess.order.entity.OrderEntity;


//with this annotation Spring will create the JPA repository proxy class and will 
//delegate the method calls to this proxy. 
@Repository
public interface OrderJPARepository extends JpaRepository<OrderEntity, UUID> {
    Optional<OrderEntity> findByTrackingId(UUID trackingId);
}
