package com.shivathmika.claimwatchai.controller;

import com.shivathmika.claimwatchai.service.FraudDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class ClaimController {

    @Autowired
    FraudDetectionService fraudService;

    @GetMapping("/")
    public String home(){
        return "ClaimWatchAI Backend Running";
    }

    @PostMapping("/check-fraud")
    public String checkFraud(@RequestBody String claimData) throws Exception {

        return fraudService.checkFraud(claimData);
    }
}