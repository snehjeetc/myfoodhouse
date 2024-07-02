package com.myfoodhouse.order.service.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myfoodhouse.dto.create.CreateOrderCommand;
import com.myfoodhouse.dto.create.CreateOrderResponse;
import com.myfoodhouse.dto.create.OrderAddress;
import com.myfoodhouse.dto.create.OrderItem;

import com.myfoodhouse.mapper.OrderDataMapper;
import com.myfoodhouse.order.domain.core.IOrderService;
import com.myfoodhouse.order.domain.core.entity.Customer;
import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.entity.Product;
import com.myfoodhouse.order.domain.core.entity.Restaurant;
import com.myfoodhouse.order.domain.core.exception.OrderDomainException;
import com.myfoodhouse.ports.input.service.OrderApplicationService;
import com.myfoodhouse.ports.output.repository.CustomerRepository;
import com.myfoodhouse.ports.output.repository.OrderRepository;
import com.myfoodhouse.ports.output.repository.RestaurantRepository;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;
import com.myfoodhouse.sys.domain.valueobjects.Money;
import com.myfoodhouse.sys.domain.valueobjects.OrderId;
import com.myfoodhouse.sys.domain.valueobjects.OrderStatus;
import com.myfoodhouse.sys.domain.valueobjects.ProductId;
import com.myfoodhouse.sys.domain.valueobjects.RestaurantId;

import lombok.extern.slf4j.Slf4j;

//Test instance by default a new instance of the class is created 
//for each test method, in that case to be able to use a beforeAll method 
//we need to use a static method and the fields should be static as well
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes=OrderTestConfigurationClass.class)
public class OrderApplicationServiceTest {

    @Autowired
    private OrderApplicationService orderApplicationService; 

    @Autowired
    private OrderDataMapper orderDataMapper; 

    @Autowired
    private OrderRepository orderRepository; 

    @Autowired 
    private CustomerRepository customerRepository; 

    @Autowired
    private RestaurantRepository restaurantRepository; 

    private CreateOrderCommand createOrderCommand; 
    private CreateOrderCommand createWrongOrderCommand; 
    private CreateOrderCommand createWrongProductPriceCommand; 
    private final UUID CUSTOMER_ID = UUID.fromString("b5a386fb-62dc-4e6b-8022-5353066e83b2"); 
    private final UUID RESTAURANT_ID = UUID.fromString("14dd56b6-2876-4164-881d-48b8b97afda9"); 
    private final UUID PRODUCT_ID = UUID.fromString("1b6d0a7f-87a8-4584-a9b2-ea1ed06625c2"); 
    private final UUID ORDER_ID = UUID.fromString("5b61e2cb-3fc7-4c26-9749-ed4aaa68ea40"); 
    private final BigDecimal PRICE = new BigDecimal("200.00"); 

    @BeforeAll
    public void init(){ 
        createOrderCommand = CreateOrderCommand.builder()
            .customerId(CUSTOMER_ID)
            .restaurantId(RESTAURANT_ID)
            .address(OrderAddress.builder()
                    .street("street-1")
                    .postalCode("100023")
                    .city("Paris")
                    .build())
            .price(PRICE)
            .items(List.of(OrderItem.builder()
                            .productId(PRODUCT_ID)
                            .quantity(1)
                            .price(new BigDecimal("50.00"))
                            .subTotal(new BigDecimal("50.00"))
                            .build(), 
                            OrderItem.builder()
                            .productId(PRODUCT_ID)
                            .quantity(3)
                            .price(new BigDecimal("50.00"))
                            .subTotal(new BigDecimal("150.00"))
                            .build()))
            .build();        
        
        createWrongOrderCommand = CreateOrderCommand.builder()
        .customerId(CUSTOMER_ID)
        .restaurantId(RESTAURANT_ID)
        .address(OrderAddress.builder()
                .street("street-1")
                .postalCode("100023")
                .city("Paris")
                .build())
        .price(new BigDecimal("250.00"))
        .items(List.of(OrderItem.builder()
                        .productId(PRODUCT_ID)
                        .quantity(1)
                        .price(new BigDecimal("50.00"))
                        .subTotal(new BigDecimal("50.00"))
                        .build(), 
                        OrderItem.builder()
                        .productId(PRODUCT_ID)
                        .quantity(3)
                        .price(new BigDecimal("50.00"))
                        .subTotal(new BigDecimal("150.00"))
                        .build()))
        .build();       
        
        createWrongProductPriceCommand = CreateOrderCommand.builder()
        .customerId(CUSTOMER_ID)
        .restaurantId(RESTAURANT_ID)
        .address(OrderAddress.builder()
                .street("street-1")
                .postalCode("100023")
                .city("Paris")
                .build())
        .price(new BigDecimal("210.00"))
        .items(List.of(OrderItem.builder()
                        .productId(PRODUCT_ID)
                        .quantity(1)
                        .price(new BigDecimal("60.00"))
                        .subTotal(new BigDecimal("60.00"))
                        .build(), 
                        OrderItem.builder()
                        .productId(PRODUCT_ID)
                        .quantity(3)
                        .price(new BigDecimal("50.00"))
                        .subTotal(new BigDecimal("150.00"))
                        .build()))
        .build(); 
        
        Customer customer = new Customer(); 
        customer.setId(new CustomerId(CUSTOMER_ID));

        Restaurant restaurantResponse = Restaurant.builder()
                                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                                .products(List.of(new Product(
                                    new ProductId(PRODUCT_ID), 
                                    "product-1", 
                                    new Money(new BigDecimal("50.00"))
                                )))
                                .active(true)
                                .build();
        
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand); 
        order.setId(new OrderId(ORDER_ID));

        when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer)); 
        
        when(restaurantRepository.findRestaurantInformation(orderDataMapper.crateOrderCommandToRestaurant(createOrderCommand)))
        .thenReturn(Optional.of(restaurantResponse)); 

        when(orderRepository.save(any(Order.class))).thenReturn(order); 

    }

    @Test
    public void testCreateOrder(){ 
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand); 
        assertEquals(createOrderResponse.getOrderStatus(), OrderStatus.PENDING);
        assertEquals(createOrderResponse.getMessage(), "Order Created Successfully");
        assertNotNull(createOrderResponse.getOrderTrackingId()); 
    }

    @Test
    public void testCreateOrderWithWrongTotalPrice() { 
        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class, 
                        () -> orderApplicationService.createOrder(createWrongOrderCommand)); 
        log.error(orderDomainException.getMessage());
        assertEquals(orderDomainException.getMessage(),
        "Total Price : 250.00 is not equal to total order price : 200.00"); 
    }

    @Test
    public void testCreateOrderWithWrongProductPrice() { 
        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class, 
                                        () -> orderApplicationService.createOrder(createWrongProductPriceCommand));
        log.error(orderDomainException.getMessage()); 
        assertEquals(orderDomainException.getMessage(), 
        "Order item price: 60.00 is not valid for product 1b6d0a7f-87a8-4584-a9b2-ea1ed06625c2");  
    }
    
    @Test
    public void testCreateOrderWithPassiveRestaurant() { 
        Restaurant restaurantResponse = Restaurant.builder()
                    .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                    .products(List.of(new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))), 
                    new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00")))))
                    .active(false)
                    .build();

        when(restaurantRepository.findRestaurantInformation(orderDataMapper.crateOrderCommandToRestaurant(createOrderCommand))).thenReturn(Optional.of(restaurantResponse));

        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class, () -> orderApplicationService.createOrder(createOrderCommand)); 
        log.error(orderDomainException.getMessage()); 
        assertEquals(orderDomainException.getMessage(), 
        "Restaurant 14dd56b6-2876-4164-881d-48b8b97afda9 not active");
    }
}
