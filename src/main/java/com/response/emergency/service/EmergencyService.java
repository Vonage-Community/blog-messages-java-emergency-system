package com.response.emergency.service;

import com.response.emergency.model.*;
import com.response.emergency.repository.IncidentRepository;
import com.response.emergency.repository.ResponderRepository;
import com.response.emergency.repository.UserRepository;
import com.response.emergency.requests.AddContactRequest;
import com.response.emergency.requests.CreateIncidentRequest;
import com.response.emergency.requests.PickIncidentRequest;
import com.response.emergency.requests.ReportIncidentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

import java.time.LocalDateTime;


@Service
@Slf4j
public class EmergencyService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResponderRepository respondentRepository;

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private VonageService vonageService;

    public AppUser createUser(AppUser user) {
        return userRepository.save(user);
    }

    public Responder createRespondent(Responder responder) {
        return respondentRepository.save(responder);
    }

    public String addUserToEmergencyList(AddContactRequest addContactRequest) {

        AppUser appUser = userRepository.findById(addContactRequest.getUserId()).orElse(null);

        assert appUser != null;
        appUser.getContactList().put(addContactRequest.getName(), addContactRequest.getPhoneNumber());
        userRepository.save(appUser);

        return "User successfully added to your emergency contact list";
    }

    public Incident createAndSendIncident(CreateIncidentRequest createIncidentRequest) {

        Incident incident = new Incident();
        incident.setUserId(createIncidentRequest.getUserId());
        incident.setIncidentDescription(createIncidentRequest.getIncidentDescription());
        incident.setStatus(IncidentStatus.UNRESOLVED.name());
        incident.setIncidentTime(LocalDateTime.now());
        incident.setRespondedTo(false);
        incident.setResponder(null);

        if (createIncidentRequest.isReportingFromScene()) {
            Map<String, Object> locationMap = locationService.getLocation();
            incident.getIncidentLocation().put("longitude", locationMap.get("longitude"));
            incident.getIncidentLocation().put("latitude", locationMap.get("latitude"));
        }
        else {
            incident.getIncidentLocation().put("longitude", createIncidentRequest.getLongitude());
            incident.getIncidentLocation().put("latitude", createIncidentRequest.getLatitude());
        }

        // send whatsapp here
        AppUser user = userRepository.findById(createIncidentRequest.getUserId()).orElse(null);
        String message = "There has been an incident reported by your friend at a location. " +
                "Please help report to authorities";

        assert user != null;
        for (Map.Entry<String, Object> entry : user.getContactList().entrySet()) {
            vonageService.sendWhatsApp(String.valueOf(entry.getValue()), message);
        }
        return incidentRepository.save(incident);
    }

    public String pickIncident(PickIncidentRequest pickIncidentRequest) {

        Incident incident = incidentRepository.findById(pickIncidentRequest.getIncidentId()).orElse(null);

        assert incident != null;
        if (incident.isRespondedTo()) {
            return "This incident has already been picked for attention";
        }

        incident.setStatus(IncidentStatus.BEING_RESOLVED.name());
        incident.setRespondedTo(true);
        incident.setResponder(pickIncidentRequest.getResponderId());

        incidentRepository.save(incident);

        return "Incident successfully picked. Kindly hurry to the scene";
    }

    public String reportIncidentStatus(ReportIncidentRequest reportIncidentRequest) {

        Incident incident = incidentRepository.findById(reportIncidentRequest.getIncidentId()).orElse(null);

        assert incident != null;
        if (!incident.isRespondedTo()) {
            return "This incident has not been picked for attention";
        }

        if (reportIncidentRequest.getStatus().equalsIgnoreCase("Abandoned")) {
            incident.setStatus(IncidentStatus.ABANDONED.name());
        }
        else {
            incident.setStatus((IncidentStatus.RESOLVED.name()));
        }

        incident.setRespondedTo(true);
        incident.setResponder(reportIncidentRequest.getResponderId());

        incidentRepository.save(incident);

        return "Incident successfully picked. Kindly hurry to the scene";
    }
}
