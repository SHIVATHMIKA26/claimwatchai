package com.shivathmika.claimwatchai.model;

import jakarta.persistence.*;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int monthsAsCustomer;
    private int age;
    private int policyDeductable;
    private double policyAnnualPremium;
    private int umbrellaLimit;
    private int incidentSeverity;
    private int numberOfVehiclesInvolved;
    private int bodilyInjuries;
    private double totalClaimAmount;

    private String fraudStatus;

    public Long getId() {
        return id;
    }

    public int getMonthsAsCustomer() {
        return monthsAsCustomer;
    }

    public void setMonthsAsCustomer(int monthsAsCustomer) {
        this.monthsAsCustomer = monthsAsCustomer;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPolicyDeductable() {
        return policyDeductable;
    }

    public void setPolicyDeductable(int policyDeductable) {
        this.policyDeductable = policyDeductable;
    }

    public double getPolicyAnnualPremium() {
        return policyAnnualPremium;
    }

    public void setPolicyAnnualPremium(double policyAnnualPremium) {
        this.policyAnnualPremium = policyAnnualPremium;
    }

    public int getUmbrellaLimit() {
        return umbrellaLimit;
    }

    public void setUmbrellaLimit(int umbrellaLimit) {
        this.umbrellaLimit = umbrellaLimit;
    }

    public int getIncidentSeverity() {
        return incidentSeverity;
    }

    public void setIncidentSeverity(int incidentSeverity) {
        this.incidentSeverity = incidentSeverity;
    }

    public int getNumberOfVehiclesInvolved() {
        return numberOfVehiclesInvolved;
    }

    public void setNumberOfVehiclesInvolved(int numberOfVehiclesInvolved) {
        this.numberOfVehiclesInvolved = numberOfVehiclesInvolved;
    }

    public int getBodilyInjuries() {
        return bodilyInjuries;
    }

    public void setBodilyInjuries(int bodilyInjuries) {
        this.bodilyInjuries = bodilyInjuries;
    }

    public double getTotalClaimAmount() {
        return totalClaimAmount;
    }

    public void setTotalClaimAmount(double totalClaimAmount) {
        this.totalClaimAmount = totalClaimAmount;
    }

    public String getFraudStatus() {
        return fraudStatus;
    }

    public void setFraudStatus(String fraudStatus) {
        this.fraudStatus = fraudStatus;
    }
}