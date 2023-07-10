package com.response.emergency.web;

import com.response.emergency.model.AppUser;
import com.response.emergency.model.Responder;
import com.response.emergency.requests.AddContactRequest;
import com.response.emergency.requests.CreateIncidentRequest;
import com.response.emergency.requests.PickIncidentRequest;
import com.response.emergency.requests.ReportIncidentRequest;
import com.response.emergency.service.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class EmergencyController {

    @Autowired
    private EmergencyService emergencyService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody AppUser user) {
        return new ResponseEntity<>(emergencyService.createUser(user), HttpStatus.OK);
    }

    @PostMapping("/create-responder")
    public ResponseEntity<?> createResponder(@RequestBody Responder responder) {
        return new ResponseEntity<>(emergencyService.createRespondent(responder), HttpStatus.OK);
    }

    @PostMapping("/add-contact")
    public ResponseEntity<?> addUserToEmergencyList(@RequestBody AddContactRequest addContactRequest) {
        return new ResponseEntity<>(emergencyService.addUserToEmergencyList(addContactRequest), HttpStatus.OK);
    }

    @PostMapping("/report-incident")
    public ResponseEntity<?> createAndSendIncident(@RequestBody CreateIncidentRequest createIncidentRequest) {
        return new ResponseEntity<>(emergencyService.createAndSendIncident(createIncidentRequest), HttpStatus.OK);
    }

    @PostMapping("/pick-incident")
    public ResponseEntity<?> pickIncident(@RequestBody PickIncidentRequest pickIncidentRequest) {
        return new ResponseEntity<>(emergencyService.pickIncident(pickIncidentRequest), HttpStatus.OK);
    }

    @PostMapping("/report-incident-status")
    public ResponseEntity<?> reportIncidentStatus(@RequestBody ReportIncidentRequest reportIncidentRequest) {
        return new ResponseEntity<>(emergencyService.reportIncidentStatus(reportIncidentRequest), HttpStatus.OK);
    }
}