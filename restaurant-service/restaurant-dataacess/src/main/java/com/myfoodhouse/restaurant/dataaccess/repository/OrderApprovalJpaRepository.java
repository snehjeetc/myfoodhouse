package com.myfoodhouse.restaurant.dataaccess.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfoodhouse.restaurant.dataaccess.entity.OrderApprovalEntity;

@Repository
public interface OrderApprovalJpaRepository extends JpaRepository<OrderApprovalEntity, UUID>{
    
}
