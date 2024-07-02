package com.myfoodhouse.order.domain.core.entity;

import java.util.List;

import com.myfoodhouse.sys.domain.entity.AggregateRoot;
import com.myfoodhouse.sys.domain.valueobjects.RestaurantId;

public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products; 
    private boolean active;

    private Restaurant (Builder builder) {
        super.setId(builder.restaurantId);
        this.products = builder.products; 
        this.active = builder.active; 
    }

    public static Builder builder() { 
        return new Builder(); 
    }

    public List<Product> getProducts() {
        return products;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    } 

    public static class Builder{ 
        private RestaurantId restaurantId; 
        private List<Product> products; 
        private boolean active;
        
        public Builder restaurantId(RestaurantId restId){ 
            this.restaurantId = restId; 
            return this; 
        }

        public Builder products(List<Product> products) { 
            this.products = products; 
            return this; 
        }

        public Builder active(boolean active) { 
            this.active = active; 
            return this; 
        }

        public Restaurant build() { 
            return new Restaurant(this); 
        }
    }
}
