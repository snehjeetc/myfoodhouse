package com.myfoodhouse.restaurant.service.domain.ports.output.repository;

import com.myfoodhouse.restaurant.service.domain.core.entity.OrderApproval;

public interface  OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
