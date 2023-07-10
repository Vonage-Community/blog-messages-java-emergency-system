package com.response.emergency.requests;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CreateIncidentRequest {

    private Long userId;

    private String incidentDescription;

    @ElementCollection
    private Map<String, Object> incidentLocation = new HashMap<>();

    private boolean reportingFromScene;

    private double longitude;

    private double latitude;
}
