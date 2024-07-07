package com.example.report.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.report.dao.ICourseDetailsDAO;
import com.example.report.entity.CourseDetails;
import com.example.report.service.ICourseMgmtService;
import com.example.report.vo.SearchInput;
import com.example.report.vo.SearchResult;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CourseMgmtServiceImpl implements ICourseMgmtService {

	@Autowired
	private ICourseDetailsDAO courseDetailsDAO;
	
	@Autowired
	private PdfHelper pdfHelper;
	
	@Override
	public Set<String> showAllCourseCategories() {
		return courseDetailsDAO.getUniqueCourseCategories();
	}

	@Override
	public Set<String> showAllTrainingMode() {
		return courseDetailsDAO.getUniqueTrainingMode();
	}

	@Override
	public Set<String> showAllFaculties() {
		return courseDetailsDAO.getUniqueFacultyNames();
	}

	@Override
	public List<SearchResult> showCoursesByFilters(SearchInput input) {
		//get NonNull and non empty String values from the inputs object and prepare entity 
		//object having that non null data and also place that entity object inside Example object.
		
		CourseDetails courseDetails=new CourseDetails();
		String category=input.getCourseCategory();
		if(ObjectUtils.isNotEmpty(category)&&StringUtils.hasLength(category)) {
			courseDetails.setCourseCategory(category);
		}
		
		String faculty=input.getFacultyName();
		if(ObjectUtils.isNotEmpty(faculty) && StringUtils.hasLength(faculty)) {
			courseDetails.setFacultyName(faculty);
		}
		
		String trainingMode=input.getTrainingMode();
		if(ObjectUtils.isNotEmpty(trainingMode)  && StringUtils.hasLength(trainingMode)) {
			courseDetails.setTrainingMode(trainingMode);
		}
		
		LocalDateTime startOn = input.getStartOn();
		if(ObjectUtils.isNotEmpty(startOn)) {
			courseDetails.setStartDate(startOn);
		}
		
		Example<CourseDetails> example=Example.of(courseDetails);
		
		//perform the search operation with Filters data of example entity object
		
		List<CourseDetails> listEntities=courseDetailsDAO.findAll(example);
		List<SearchResult> listResult=new ArrayList<>();
		listEntities.forEach(course->{
			SearchResult result=new SearchResult();
			BeanUtils.copyProperties(course, result);
			listResult.add(result);
		});
		return listResult;
	}

	 @Override
	    public void generatePdfReport(SearchInput input, HttpServletResponse response) {
	        List<SearchResult> coursesByFilters = showCoursesByFilters(input);

	        pdfHelper.generatePdfReport(input, response, coursesByFilters);
	    }



	@Override
    public void generateExcelReport(SearchInput input, HttpServletResponse response) {
        // Get the search result
        List<SearchResult> coursesByFilters = showCoursesByFilters(input);

        // Create a workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // Create a sheet
        HSSFSheet sheet = workbook.createSheet("Course Report");

        // Create a header row
        Row headerRow = sheet.createRow(0);

        String[] headers = {
            "Course ID", "Course Name", "Location", "Course Category", 
            "Faculty Name", "Fee", "Admin Contact", "Training Mode", "Start Date"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(createHeaderCellStyle(workbook));
        }

        // Create data rows
        int rowCount = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (SearchResult result : coursesByFilters) {
            Row row = sheet.createRow(rowCount++);

            row.createCell(0).setCellValue(result.getCourseId());
            row.createCell(1).setCellValue(result.getCourseName());
            row.createCell(2).setCellValue(result.getLocation());
            row.createCell(3).setCellValue(result.getCourseCategory());
            row.createCell(4).setCellValue(result.getFacultyName());
            row.createCell(5).setCellValue(result.getFee());
            row.createCell(6).setCellValue(result.getAdminContact());
            row.createCell(7).setCellValue(result.getTrainingMode());
            row.createCell(8).setCellValue(result.getStartDate().format(formatter));
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Set content type and headers
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=course_report.xls");

        // Write to response output stream
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerCellStyle.setFont(font);
        return headerCellStyle;
    }

}
