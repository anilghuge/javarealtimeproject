package com.example.report.service.impl;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.report.vo.SearchInput;
import com.example.report.vo.SearchResult;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfHelper {

	    public void generatePdfReport(SearchInput input, HttpServletResponse response,List<SearchResult> coursesByFilters) {

	        // Set content type
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=course_report.pdf");

	        Document document = new Document();
	        try {
	            PdfWriter.getInstance(document, response.getOutputStream());
	            document.open();

	            // Add title
	            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
	            Paragraph title = new Paragraph("Course Report", titleFont);
	            title.setAlignment(Element.ALIGN_CENTER);
	            document.add(title);

	            // Add a table
	            PdfPTable table = new PdfPTable(9); // 9 columns
	            table.setWidthPercentage(100);
	            table.setSpacingBefore(10f);

	            // Define column widths
	            float[] columnWidths = {1f, 3f, 2f, 2f, 3f, 2f, 2f, 2f, 2f};
	            table.setWidths(columnWidths);

	            // Add table headers
	            addTableHeader(table);

	            // Add rows
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	            for (SearchResult result : coursesByFilters) {
	                table.addCell(result.getCourseId().toString());
	                table.addCell(result.getCourseName());
	                table.addCell(result.getLocation());
	                table.addCell(result.getCourseCategory());
	                table.addCell(result.getFacultyName());
	                table.addCell(result.getFee().toString());
	                table.addCell(result.getAdminContact());
	                table.addCell(result.getTrainingMode());
	                table.addCell(result.getStartDate().format(formatter));
	            }

	            document.add(table);
	            document.close();
	        } catch (DocumentException | IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private void addTableHeader(PdfPTable table) {
	        Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
	        String[] headers = {
	            "Course ID", "Course Name", "Location", "Course Category", 
	            "Faculty Name", "Fee", "Admin Contact", "Training Mode", "Start Date"
	        };

	        for (String header : headers) {
	            PdfPCell headerCell = new PdfPCell();
	            headerCell.setPhrase(new Phrase(header, headerFont));
	            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(headerCell);
	        }
	    }
}
