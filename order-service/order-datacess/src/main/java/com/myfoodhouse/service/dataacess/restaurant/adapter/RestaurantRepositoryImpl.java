package com.myfoodhouse.service.dataacess.restaurant.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.myfoodhouse.order.domain.core.entity.Restaurant;
import com.myfoodhouse.ports.output.repository.RestaurantRepository;
import com.myfoodhouse.service.dataacess.restaurant.mapper.RestaurantDataAccessMapper;
import com.myfoodhouse.system.dataaccess.restaurant.repository.RestaurantJpaRepository;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    
    private final RestaurantJpaRepository restaurantJpaRepository; 
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;
    public RestaurantRepositoryImpl(RestaurantJpaRepository restaurantJpaRepository,
            RestaurantDataAccessMapper restaurantDataAccessMapper) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.restaurantDataAccessMapper = restaurantDataAccessMapper;
    }
    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        return restaurantJpaRepository.findByRestaurantIdAndProductIdIn(restaurant.getId().getValue(), restaurantDataAccessMapper.restaurantToRestaurantProducts(
            restaurant
        )).map(restaurantDataAccessMapper::restaurantEntityToRestaurant); 
    } 

   
    
}
