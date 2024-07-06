package com.myfoodhouse.service.dataacess.order.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.valueobject.TrackingId;
import com.myfoodhouse.ports.output.repository.OrderRepository;
import com.myfoodhouse.service.dataacess.order.mapper.OrderDataAccessMapper;
import com.myfoodhouse.service.dataacess.order.repository.OrderJPARepository;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    

    private final OrderJPARepository orderJPARepository; 
    private final OrderDataAccessMapper orderDataAccessMapper; 

    public OrderRepositoryImpl(OrderJPARepository orderJPARepository, OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJPARepository = orderJPARepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrderDomain(
            orderJPARepository.save(orderDataAccessMapper.orderToOrderEntity(order))
        ); 
    }



    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJPARepository.findByTrackingId(trackingId.getValue())
                    .map(orderDataAccessMapper::orderEntityToOrderDomain); 
    }
    
}
