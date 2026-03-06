package com.shivathmika.claimwatchai.controller;

import com.shivathmika.claimwatchai.model.Claim;
import com.shivathmika.claimwatchai.repository.ClaimRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FraudController {

    @Autowired
    private ClaimRepository claimRepository;

    @PostMapping("/check-fraud")
    public Map<String, Object> checkFraud(@RequestBody Claim claim) {

        RestTemplate restTemplate = new RestTemplate();

        String mlUrl = "http://localhost:5001/predict";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();

        request.put("monthsAsCustomer", claim.getMonthsAsCustomer());
        request.put("age", claim.getAge());
        request.put("policyDeductable", claim.getPolicyDeductable());
        request.put("policyAnnualPremium", claim.getPolicyAnnualPremium());
        request.put("umbrellaLimit", claim.getUmbrellaLimit());
        request.put("incidentSeverity", claim.getIncidentSeverity());
        request.put("numberOfVehiclesInvolved", claim.getNumberOfVehiclesInvolved());
        request.put("bodilyInjuries", claim.getBodilyInjuries());
        request.put("totalClaimAmount", claim.getTotalClaimAmount());

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(mlUrl, entity, Map.class);

        String prediction = (String) response.getBody().get("prediction");
        Double probability = ((Number) response.getBody().get("fraudProbability")).doubleValue();

        claim.setFraudStatus(prediction);

        claimRepository.save(claim);

        Map<String, Object> result = new HashMap<>();
        result.put("prediction", prediction);
        result.put("fraudProbability", probability);

        return result;
    }
}