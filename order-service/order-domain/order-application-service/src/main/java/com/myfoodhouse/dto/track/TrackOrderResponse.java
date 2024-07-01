package com.myfoodhouse.dto.track;

import java.util.List;
import java.util.UUID;

import com.myfoodhouse.sys.domain.valueobjects.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {
    
    @NotNull
    private final UUID orderTrackingId; 
    
    @NotNull
    private final OrderStatus orderStatus; 
    private final List<String> failureMessages; 
}
