package com.myfoodhouse.restaurant.service.domain.ports.output.repository;

import java.util.Optional;

import com.myfoodhouse.restaurant.service.domain.core.entity.Restaurant;

public interface RestaurantRepository {
     Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
