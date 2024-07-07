package com.myfoodhouse.service.dataacess.restaurant.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RestaurantEntityId.class)
@Table(name="order_restaurant_m_view", schema= "restaurant")
@Entity
public class RestaurantEntity {
    
    @Id
    private UUID restaurantId; 
    @Id
    private UUID productId; 
    private String restaurantName; 
    private Boolean restaurantActive; 
    private String productName; 
    private BigDecimal productPrice;
    @Override
    public int hashCode() {
      return Objects.hash(restaurantId, productId); 
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RestaurantEntity other = (RestaurantEntity) obj;
        if (restaurantId == null) {
            if (other.restaurantId != null)
                return false;
        } else if (!restaurantId.equals(other.restaurantId))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        return true;
    } 

    

}
