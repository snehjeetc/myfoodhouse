package com.myfoodhouse.restaurant.service.domain.mapper;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.myfoodhouse.restaurant.service.domain.core.entity.OrderDetail;
import com.myfoodhouse.restaurant.service.domain.core.entity.Product;
import com.myfoodhouse.restaurant.service.domain.core.entity.Restaurant;
import com.myfoodhouse.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.myfoodhouse.sys.domain.valueobjects.Money;
import com.myfoodhouse.sys.domain.valueobjects.OrderId;
import com.myfoodhouse.sys.domain.valueobjects.OrderStatus;
import com.myfoodhouse.sys.domain.valueobjects.RestaurantId;

@Component
public class RestaurantDataMapper {
    public Restaurant restaurantApprovalRequestToRestaurant(RestaurantApprovalRequest
                                                                             restaurantApprovalRequest) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(UUID.fromString(restaurantApprovalRequest.getRestaurantId())))
                .orderDetail(OrderDetail.builder()
                        .orderId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())))
                        .products(restaurantApprovalRequest.getProducts().stream().map(
                                product -> Product.builder()
                                        .productId(product.getId())
                                        .quantity(product.getQuantity())
                                        .build())
                                .collect(Collectors.toList()))
                        .totalAmount(new Money(restaurantApprovalRequest.getPrice()))
                        .orderStatus(OrderStatus.valueOf(restaurantApprovalRequest.getRestaurantOrderStatus().name()))
                        .build())
                .build();
    }
}
