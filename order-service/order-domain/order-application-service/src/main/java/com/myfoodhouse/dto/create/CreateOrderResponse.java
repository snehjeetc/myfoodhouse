package com.myfoodhouse.dto.create;

import java.util.UUID;

import com.myfoodhouse.sys.domain.valueobjects.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    
    @NotNull
    private final UUID orderTrackingId; 

    @NotNull
    private final OrderStatus orderStatus; 

    @NotNull 
    private final String message; 

}
