package com.myfoodhouse.ports.output.repository;

import java.util.Optional;

import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.valueobject.TrackingId;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByTrackingId(TrackingId trackingId); 
}
