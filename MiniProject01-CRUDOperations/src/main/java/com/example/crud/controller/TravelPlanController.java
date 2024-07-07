package com.example.crud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.entity.TravelPlan;
import com.example.crud.service.ITravelPlanMgmtService;

@RestController
@RequestMapping("/travelplans")
public class TravelPlanController {

    @Autowired
    private ITravelPlanMgmtService travelPlanMgmtService;

    @PostMapping("/register")
    public ResponseEntity<String> registerTravelPlan(@RequestBody TravelPlan plan) {
        try {
            String response = travelPlanMgmtService.registerTravelPlan(plan);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering travel plan: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<Integer, String>> getTravelPlanCategories() {
        try {
            Map<Integer, String> categories = travelPlanMgmtService.getTravelPlanCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TravelPlan>> showAllTravelPlans() {
        try {
            List<TravelPlan> plans = travelPlanMgmtService.showAllTravelPlans();
            return new ResponseEntity<>(plans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<TravelPlan> showTravelPlanById(@PathVariable("id") Integer planId) {
        try {
            TravelPlan plan = travelPlanMgmtService.showTravelPlanById(planId);
            return new ResponseEntity<>(plan, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTravelPlan(@RequestBody TravelPlan plan) {
        try {
            String response = travelPlanMgmtService.updateTravlePlan(plan);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating travel plan: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTravelPlan(@PathVariable("id") Integer planId) {
        try {
            String response = travelPlanMgmtService.deleteTravlePlan(planId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting travel plan: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/changeStatus/{id}/{status}")
    public ResponseEntity<String> changeTravelPlanStatus(@PathVariable("id") Integer planId, @PathVariable String status) {
        try {
            String response = travelPlanMgmtService.changeTravlePlan(planId, status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error changing travel plan status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

