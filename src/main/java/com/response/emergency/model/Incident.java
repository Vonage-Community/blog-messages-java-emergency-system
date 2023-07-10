package com.response.emergency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String incidentDescription;

    @ElementCollection
    private Map<String, Object> incidentLocation = new HashMap<>();

    private LocalDateTime incidentTime;

    private String status;

    private boolean respondedTo;

    private Long responder;
}
