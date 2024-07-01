package com.myfoodhouse.order.domain.core.entity;

import com.myfoodhouse.order.domain.core.valueobject.OrderItemId;
import com.myfoodhouse.sys.domain.entity.BaseEntity;
import com.myfoodhouse.sys.domain.valueobjects.Money;
import com.myfoodhouse.sys.domain.valueobjects.OrderId;

// Note : We can use Lombok properties for builder pattern, but that will make the core/domain logic dependent 
//of external framework which is undesirable and not mentioned in the clean architecture as well. 
//In other modules the Like Infrastructure modules, the boiler plate java code will be replaced by Lombok annotation. 
public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId; 
    private final Product product; 
    private final int quantity; 
    private final Money price; 
    private final Money subTotal; 

    private OrderItem(Builder builder){ 
        super.setId(builder.orderItemId);
        this.product = builder.product; 
        this.quantity = builder.quantity; 
        this.price = builder.price; 
        this.subTotal = builder.subTotal; 
    }

    public static Builder builder() { 
        return new Builder(); 
    }

    public static final class Builder { 
        private OrderItemId orderItemId; 
        private Product product; 
        private int quantity; 
        private Money price; 
        private Money subTotal;

        private Builder(){

        }

        public Builder orderItemId(OrderItemId id){ 
            this.orderItemId = id; 
            return this; 
        }

        public Builder product(Product val){ 
            this.product = val;
            return this;  
        }

        public Builder quantity(int val) { 
            this.quantity = val; 
            return this; 
        }

        public Builder price(Money price) { 
            this.price = price; 
            return this; 
        }            

        public Builder subTotal(Money subTot){ 
            this.subTotal = subTot; 
            return this; 
        }

        public OrderItem build() { 
            return new OrderItem(this); 
        }
    }

    public OrderId getOrderId() {
        return orderId;
    }



    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }



    public Product getProduct() {
        return product;
    }



    public int getQuantity() {
        return quantity;
    }



    public Money getPrice() {
        return price;
    }



    public Money getSubTotal() {
        return subTotal;
    }

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        setOrderId(orderId);
        super.setId(orderItemId);
    }

    boolean isPriceValid() { 
        return price.isGreaterThanZero() && 
               price.equals(product.getPrice()) && 
               price.multiply(quantity).equals(subTotal); 
    }
}
