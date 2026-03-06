package com.shivathmika.claimwatchai.controller;

import com.shivathmika.claimwatchai.model.Claim;
import com.shivathmika.claimwatchai.repository.ClaimRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClaimController {

    @Autowired
    private ClaimRepository claimRepository;

    @GetMapping("/claims")
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}