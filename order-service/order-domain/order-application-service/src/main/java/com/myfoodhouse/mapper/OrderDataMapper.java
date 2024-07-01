package com.myfoodhouse.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.myfoodhouse.dto.create.CreateOrderCommand;
import com.myfoodhouse.dto.create.CreateOrderResponse;
import com.myfoodhouse.dto.create.OrderAddress;
import com.myfoodhouse.order.domain.core.entity.OrderItem;
import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.entity.Product;
import com.myfoodhouse.order.domain.core.entity.Restaurant;
import com.myfoodhouse.order.domain.core.valueobject.StreetAddress;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;
import com.myfoodhouse.sys.domain.valueobjects.Money;
import com.myfoodhouse.sys.domain.valueobjects.ProductId;
import com.myfoodhouse.sys.domain.valueobjects.RestaurantId;

@Component
public class OrderDataMapper {
    
    public Restaurant crateOrderCommandToRestaurant(CreateOrderCommand createOrderCommand){ 
        return Restaurant.builder()
                        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                        .products(
                            createOrderCommand.getItems().stream()
                            .map(orderItem -> new Product(new ProductId(orderItem.getProductId())))
                            .collect(Collectors.toList()))
                        .build(); 

    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) { 
        return Order.builder()
                    .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                    .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                    .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                    .price(new Money(createOrderCommand.getPrice()))
                    .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                    .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(List<com.myfoodhouse.dto.create.OrderItem> items) {
        return items.stream()
                    .map(orderItem -> OrderItem.builder()
                                                .product(new Product(new ProductId(orderItem.getProductId())))
                                                .price(new Money(orderItem.getPrice()))
                                                .quantity(orderItem.getQuantity())
                                                .subTotal(new Money(orderItem.getSubTotal()))
                                                .build())
                    .collect(Collectors.toList()); 

    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) { 
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build(); 
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
            address.getCity(),  
            UUID.randomUUID(),
            address.getPostalCode(), 
            address.getStreet()
        );
    }
}
