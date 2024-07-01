package com.myfoodhouse.dto.message;

import java.time.Instant;
import java.util.List;

import com.myfoodhouse.sys.domain.valueobjects.OrderApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {
    
    private String id; 
    private String sagaId; 
    private String orderId; 
    private String restaurantId; 
    private Instant createdAt; 
    private OrderApprovalStatus orderApprovalStatus;
    List<String> failureMessages;  
}
