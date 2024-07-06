package com.myfoodhouse.service.dataacess.order.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@Table(name = "orders_items")
@IdClass(OrderItemEntityId.class)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItemEntity {

    @Id
    private Long id; 

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private OrderEntity order;
    
    private UUID productId; 
    private BigDecimal price;
    private Integer quantity; 
    private BigDecimal subTotal;
    @Override
    public int hashCode() {
       return Objects.hash(id, order); 
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItemEntity other = (OrderItemEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        return true;
    }
    
    
}
