package com.myfoodhouse.ports.output.message.publisher.restaurant;

import com.myfoodhouse.order.domain.core.event.OrderPaidEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestPublisher extends DomainEventPublisher<OrderPaidEvent>{
    
}
