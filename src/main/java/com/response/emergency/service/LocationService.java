package com.response.emergency.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class LocationService {

    Map<String, Object> getLocation() {

        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("longitude", generateDummyCoordinate());
        coordinates.put("latitude", generateDummyCoordinate());

        return coordinates;

    }

    public static double generateDummyCoordinate() {
        double minValue = 111.00;
        double maxValue = 999.99;

        Random random = new Random();

        return minValue + (maxValue - minValue) * random.nextDouble();
    }
}

