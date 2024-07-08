package com.myfoodhouse.restaurant.service.domain.dto;

import com.myfoodhouse.restaurant.service.domain.core.entity.Product;
import com.myfoodhouse.sys.domain.valueobjects.RestaurantOrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalRequest {
    private String id;
    private String sagaId;
    private String restaurantId;
    private String orderId;
    private RestaurantOrderStatus restaurantOrderStatus;
    private java.util.List<Product> products;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;
}