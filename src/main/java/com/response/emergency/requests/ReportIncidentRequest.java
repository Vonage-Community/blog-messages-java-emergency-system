package com.response.emergency.requests;

import lombok.Getter;

@Getter
public class ReportIncidentRequest {

    private Long incidentId;

    private Long responderId;

    private String status;
}
