package com.myfoodhouse.restaurant.dataaccess.adapter;

import org.springframework.stereotype.Component;

import com.myfoodhouse.restaurant.dataaccess.mapper.RestaurantDataAccessMapper;
import com.myfoodhouse.restaurant.dataaccess.repository.OrderApprovalJpaRepository;
import com.myfoodhouse.restaurant.service.domain.core.entity.OrderApproval;
import com.myfoodhouse.restaurant.service.domain.ports.output.repository.OrderApprovalRepository;


@Component
public class OrderApprovalRepositoryImpl implements OrderApprovalRepository{

   private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    public OrderApprovalRepositoryImpl(OrderApprovalJpaRepository orderApprovalJpaRepository,
                                       RestaurantDataAccessMapper restaurantDataAccessMapper) {
        this.orderApprovalJpaRepository = orderApprovalJpaRepository;
        this.restaurantDataAccessMapper = restaurantDataAccessMapper;
    }

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        return restaurantDataAccessMapper
                .orderApprovalEntityToOrderApproval(orderApprovalJpaRepository
                        .save(restaurantDataAccessMapper.orderApprovalToOrderApprovalEntity(orderApproval)));
    }

}
