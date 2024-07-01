package com.myfoodhouse;

import org.springframework.stereotype.Component;

import com.myfoodhouse.dto.track.TrackOrderQuery;
import com.myfoodhouse.dto.track.TrackOrderResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderTrackCommandHandler {
    
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }
}
