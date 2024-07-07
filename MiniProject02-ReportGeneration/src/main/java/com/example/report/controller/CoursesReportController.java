package com.example.report.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.report.service.ICourseMgmtService;
import com.example.report.vo.SearchInput;
import com.example.report.vo.SearchResult;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reporting/api")
public class CoursesReportController {

    @Autowired
    private ICourseMgmtService courseMgmtService;

    @GetMapping("/showallcoursecategories")
    public ResponseEntity<?> showAllCourseCategories() {
        try {
            Set<String> courseCategories = courseMgmtService.showAllCourseCategories();
            return new ResponseEntity<>(courseCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/showalltrainingmode")
    public ResponseEntity<?> showAllTrainingModes() {
        try {
            Set<String> trainingModes = courseMgmtService.showAllTrainingMode();
            return new ResponseEntity<>(trainingModes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/showallfaculties")
    public ResponseEntity<?> showAllFaculties() {
        try {
            Set<String> faculties = courseMgmtService.showAllFaculties();
            return new ResponseEntity<>(faculties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/showcoursesbyfilters")
    public ResponseEntity<?> showCoursesByFilters(@RequestBody SearchInput input) {
        try {
            List<SearchResult> searchResults = courseMgmtService.showCoursesByFilters(input);
            return new ResponseEntity<>(searchResults, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/generatepdfreport")
    public void generatePdfReport(@RequestBody SearchInput input, HttpServletResponse response) {
        try {
            courseMgmtService.generatePdfReport(input, response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("text/plain");
            try {
                response.getWriter().write(e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @PostMapping("/generateexcelreport")
    public void generateExcelReport(@RequestBody SearchInput input, HttpServletResponse response) {
        try {
            courseMgmtService.generateExcelReport(input, response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("text/plain");
            try {
                response.getWriter().write(e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}