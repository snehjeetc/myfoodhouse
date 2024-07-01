package com.myfoodhouse.order.domain.core.entity;

import java.util.List;
import java.util.UUID;

import com.myfoodhouse.order.domain.core.exception.OrderDomainException;
import com.myfoodhouse.order.domain.core.valueobject.OrderItemId;
import com.myfoodhouse.order.domain.core.valueobject.StreetAddress;
import com.myfoodhouse.order.domain.core.valueobject.TrackingId;
import com.myfoodhouse.sys.domain.entity.AggregateRoot;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;
import com.myfoodhouse.sys.domain.valueobjects.Money;
import com.myfoodhouse.sys.domain.valueobjects.OrderId;
import com.myfoodhouse.sys.domain.valueobjects.OrderStatus;
import com.myfoodhouse.sys.domain.valueobjects.RestaurantId;

public class Order extends AggregateRoot<OrderId>{
    private final CustomerId customerId; 
    private final RestaurantId restaurantId; 
    private final StreetAddress deliveryAddress; 
    private final Money price; 
    private final List<OrderItem> items; 

    //These states will be changed during operations. 
    private TrackingId trackingId;
    private OrderStatus orderStatus; 
    private List<String> failureMessages;  

    private Order(Builder builder) { 
        super.setId(builder.orderId);
        this.customerId = builder.customerId; 
        this.restaurantId = builder.restaurantId; 
        this.deliveryAddress = builder.deliveryAddress; 
        this.price = builder.price; 
        this.items = builder.items; 
        this.trackingId = builder.trackingId; 
        this.failureMessages = builder.failureMessages; 
        this.orderStatus = builder.orderStatus; 
    }

    public static Builder builder() { 
        return new Builder(); 
    }

    public static final class Builder{ 
        private OrderId orderId; 
        private CustomerId customerId; 
        private RestaurantId restaurantId; 
        private StreetAddress deliveryAddress; 
        private Money price; 
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus; 
        private List<String> failureMessages;  
        public Builder orderId(OrderId orderId) { 
            this.orderId = orderId; 
            return this; 
        }
        public Builder customerId(CustomerId customerId) { 
            this.customerId = customerId; 
            return this; 
        }
        public Builder restaurantId(RestaurantId restaurantId){ 
            this.restaurantId = restaurantId; 
            return this; 
        }
        public Builder deliveryAddress(StreetAddress deliveryAddress) { 
            this.deliveryAddress = deliveryAddress; 
            return this; 
        }
        public Builder price(Money price) { 
            this.price = price; 
            return this; 
        }
        public Builder items(List<OrderItem> items){ 
            this.items = items; 
            return this; 
        }
        public Builder trackingId(TrackingId trackingId){ 
            this.trackingId = trackingId; 
            return this; 
        }
        public Builder orderStatus(OrderStatus orderStatus){ 
            this.orderStatus = orderStatus; 
            return this; 
        }
        public Builder failureMessages(List<String> failMessages) { 
            this.failureMessages = failMessages; 
            return this; 
        }
        public Order build() { 
            //Now there can be a possibilities that few fields are not set here, the final fields
            //and can contain null, values that will be checked in validations
            return new Order(this); 
        }
    }

    public void pay() { 
        if(orderStatus != OrderStatus.PENDING)
            throw new OrderDomainException("Order is not in correct state for payment"); 
        orderStatus = OrderStatus.PAID; 
    }

    public void approve() { 
        if(orderStatus != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in correct state for approval"); 
        orderStatus = OrderStatus.APPROVED; 
    }

    public void initCancel(List<String> failureMessages) { 
        if(orderStatus != OrderStatus.PAID) { 
            throw new OrderDomainException("Order is not in correct status for initializing cancellation"); 
        }
        orderStatus = OrderStatus.CANCELLING; 
        updateFailureMessages(failureMessages); 
    }

    private void updateFailureMessages(List<String> pfailureMessages) {
        if(this.failureMessages != null && pfailureMessages != null)
            this.failureMessages.addAll(pfailureMessages.stream().filter(message -> !message.isEmpty()).toList()); 
        if(this.failureMessages == null)
            this.failureMessages = pfailureMessages; 
    }

    public void cancel(List<String> failureMessages) { 
        if(orderStatus !=OrderStatus.PENDING || orderStatus != OrderStatus.CANCELLING)
            throw new OrderDomainException("Order is not in correct status for cancellation"); 
        orderStatus = OrderStatus.CANCELLED; 
        updateFailureMessages(failureMessages);
    }

    public void initializeOrder() { 
        setId(new OrderId(UUID.randomUUID()));
        this.trackingId = new TrackingId(UUID.randomUUID()); 
        orderStatus = OrderStatus.PENDING; 
        initializeOrderItems(); 
    }

    private void initializeOrderItems() { 
        long itemId = 1; 
        for(OrderItem orderItem : items) { 
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++)); 
        }
    }

    public void validateOrder() { 
        validateInitialOrder(); 
        validateTotalPrice(); 
        validateItemsPrice(); 
    }

    private void validateItemsPrice() {
        Money orderItemTotal = items.stream().map(order -> {
            validateItemPrice(order); 
            return order.getSubTotal(); 
        }).reduce(Money.ZERO, Money::addMoney); 
        if(!orderItemTotal.equals(this.price))
            throw new OrderDomainException("Total Price : " + price.getAmount() + " is not equal to total order price : " + orderItemTotal.getAmount()); 
    }

    private void validateItemPrice(OrderItem order) {
        if(!order.isPriceValid())
            throw new OrderDomainException("Order item price: " + order.getPrice().getAmount() + " is not valid for product " + order.getProduct().getId().getValue()); 
    }

    private void validateTotalPrice() {
        if(price == null || !price.isGreaterThanZero()){ 
            throw new OrderDomainException("Total price must be greater than zero"); 
        }
    }

    private void validateInitialOrder() {
        if(orderStatus != null || getId() != null) { 
            throw new OrderDomainException("Order is not in correct state for initialization"); 
        }
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }
}
