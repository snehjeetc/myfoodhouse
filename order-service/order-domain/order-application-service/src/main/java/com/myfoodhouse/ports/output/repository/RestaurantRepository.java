package com.myfoodhouse.ports.output.repository;

import java.util.Optional;

import com.myfoodhouse.order.domain.core.entity.Restaurant;

public interface RestaurantRepository {
    
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant); 
}
