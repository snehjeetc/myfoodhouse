package com.myfoodhouse.restaurant.dataaccess.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.myfoodhouse.restaurant.dataaccess.mapper.RestaurantDataAccessMapper;
import com.myfoodhouse.restaurant.service.domain.core.entity.Restaurant;
import com.myfoodhouse.restaurant.service.domain.ports.output.repository.RestaurantRepository;
import com.myfoodhouse.system.dataaccess.restaurant.entity.RestaurantEntity;
import com.myfoodhouse.system.dataaccess.restaurant.repository.RestaurantJpaRepository;

public class RestaurantRepositoryImpl implements RestaurantRepository{

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    public RestaurantRepositoryImpl(RestaurantJpaRepository restaurantJpaRepository,
                                    RestaurantDataAccessMapper restaurantDataAccessMapper) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.restaurantDataAccessMapper = restaurantDataAccessMapper;
    }

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        List<UUID> restaurantProducts =
                restaurantDataAccessMapper.restaurantToRestaurantProducts(restaurant);
        Optional<List<RestaurantEntity>> restaurantEntities = restaurantJpaRepository
                .findByRestaurantIdAndProductIdIn(restaurant.getId().getValue(),
                        restaurantProducts);
        return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }
    
}
