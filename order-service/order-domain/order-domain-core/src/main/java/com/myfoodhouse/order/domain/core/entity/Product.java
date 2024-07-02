package com.myfoodhouse.order.domain.core.entity;

import com.myfoodhouse.sys.domain.entity.BaseEntity;
import com.myfoodhouse.sys.domain.valueobjects.Money;
import com.myfoodhouse.sys.domain.valueobjects.ProductId;

public class Product extends BaseEntity<ProductId> {
    private String name; 
    private Money price;

    public Product(ProductId id) { 
        super.setId(id);
    }

    public Product(ProductId id, String name, Money price) {
        this(id); 
        this.name = name;
        this.price = price;
    }

    public Product(String name, Money price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public Money getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Money price) {
        this.price = price;
    } 

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name; 
        this.price = price; 
    }
    

    
}
