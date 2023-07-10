package com.response.emergency.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickIncidentRequest {

    private Long incidentId;

    private Long responderId;
}
