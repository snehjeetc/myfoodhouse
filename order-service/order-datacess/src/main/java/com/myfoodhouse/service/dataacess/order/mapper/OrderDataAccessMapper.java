package com.myfoodhouse.service.dataacess.order.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.entity.OrderItem;
import com.myfoodhouse.order.domain.core.entity.Product;
import com.myfoodhouse.order.domain.core.valueobject.OrderItemId;
import com.myfoodhouse.order.domain.core.valueobject.StreetAddress;
import com.myfoodhouse.order.domain.core.valueobject.TrackingId;
import com.myfoodhouse.service.dataacess.order.entity.OrderAddressEntity;
import com.myfoodhouse.service.dataacess.order.entity.OrderEntity;
import com.myfoodhouse.service.dataacess.order.entity.OrderItemEntity;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;
import com.myfoodhouse.sys.domain.valueobjects.Money;
import com.myfoodhouse.sys.domain.valueobjects.OrderId;
import com.myfoodhouse.sys.domain.valueobjects.ProductId;
import com.myfoodhouse.sys.domain.valueobjects.RestaurantId;

@Component
public class OrderDataAccessMapper {
    
    public OrderEntity orderToOrderEntity(Order order) { 
        OrderEntity orderEntity  = OrderEntity.builder()
                    .id(order.getId().getValue())
                    .customerId(order.getCustomerId().getValue())
                    .restaurantId(order.getRestaurantId().getValue())
                    .trackingId(order.getTrackingId().getValue())
                    .address(deliveryAddressToAddressEntity(order.getDeliveryAddress()))
                    .price(order.getPrice().getAmount())
                    .items(orderItemsToOrderItemEntity(order.getItems()))
                    .orderStatus(order.getOrderStatus())
                    .failureMessages(order.getFailureMessages() != null ? String.join(Order.FAILURE_MESSAGE_DELIMETER, order.getFailureMessages()) : "")
                    .build();
        orderEntity.getAddress().setOrder(orderEntity);
        orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));
        return orderEntity;  
    }

    public Order orderEntityToOrderDomain(OrderEntity orderEntity) { 
        return Order.builder()
                    .orderId(new OrderId(orderEntity.getId()))
                    .customerId(new CustomerId(orderEntity.getCustomerId()))
                    .restaurantId(new RestaurantId(orderEntity.getRestaurantId()))
                    .deliveryAddress(addressEntityToDeliveryAddress(orderEntity.getAddress()))
                    .price(new Money(orderEntity.getPrice()))
                    .items(orderItemEntitiesToOrderItem(orderEntity.getItems()))
                    .trackingId(new TrackingId(orderEntity.getId()))
                    .failureMessages(orderEntity.getFailureMessages().isEmpty() ? new ArrayList<>() : new ArrayList<>(Arrays.asList(orderEntity.getFailureMessages().split(Order.FAILURE_MESSAGE_DELIMETER))))
                    .build(); 
    }

    private List<OrderItem> orderItemEntitiesToOrderItem(List<OrderItemEntity> items) {
        return items.stream()
                    .map(orderItemEntity -> OrderItem.builder()
                                            .orderItemId(new OrderItemId(orderItemEntity.getId()))
                                            .product(new Product(new ProductId(orderItemEntity.getProductId())))
                                            .price(new Money(orderItemEntity.getPrice()))
                                            .quantity(orderItemEntity.getQuantity())
                                            .subTotal(new Money(orderItemEntity.getSubTotal()))
                                            .build())
                    .collect(Collectors.toList()); 
    }

    private StreetAddress addressEntityToDeliveryAddress(OrderAddressEntity address) {
        return new StreetAddress(address.getCity(), address.getId(), address.getPostalCode(), address.getStreet()); 
    }

    private List<OrderItemEntity> orderItemsToOrderItemEntity(List<OrderItem> items) {
        return items.stream()
                    .map(orderItem -> OrderItemEntity.builder()
                                    .id(orderItem.getId().getValue())
                                    .productId(orderItem.getProduct().getId().getValue())
                                    .price(orderItem.getPrice().getAmount())
                                    .quantity(orderItem.getQuantity())
                                    .subTotal(orderItem.getSubTotal().getAmount())
                                    .build())
                    .collect(Collectors.toList()); 
    }   

    private OrderAddressEntity deliveryAddressToAddressEntity(StreetAddress deliveryAddress) {
        return OrderAddressEntity.builder()
                    .id(deliveryAddress.getId())
                    .street(deliveryAddress.getStreet())
                    .city(deliveryAddress.getCity())
                    .postalCode(deliveryAddress.getPostalCode())
                    .build(); 
    }
}
