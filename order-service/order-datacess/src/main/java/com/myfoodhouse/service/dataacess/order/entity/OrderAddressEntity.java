package com.myfoodhouse.service.dataacess.order.entity;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Table(name = "order_address")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderAddressEntity {

    @Id
    private UUID id; 

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ORDER_ID")
    private OrderEntity order; 
    
    private String street; 
    private String postalCode; 
    private String city;
    @Override
    public int hashCode() {
        return Objects.hash(id); 
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderAddressEntity other = (OrderAddressEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    } 

    
}
