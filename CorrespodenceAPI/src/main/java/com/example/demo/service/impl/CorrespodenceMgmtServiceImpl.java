package com.example.demo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.CoSummary;
import com.example.demo.entity.COTriggersEntity;
import com.example.demo.entity.CitizenApplicationRegistrationEntity;
import com.example.demo.entity.DCCaseEntity;
import com.example.demo.entity.ElibilityDetailsEntity;
import com.example.demo.repository.IApplicationRegistrationRepository;
import com.example.demo.repository.ICOTriggerRepository;
import com.example.demo.repository.IDCCaseRepository;
import com.example.demo.repository.IEligibilityDeterminationRepository;
import com.example.demo.service.ICorrespodenceMgmtService;
import com.example.demo.utils.EmailUtils;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class CorrespodenceMgmtServiceImpl implements ICorrespodenceMgmtService {

	@Autowired
	private ICOTriggerRepository coTriggerDAO;

	@Autowired
	private IEligibilityDeterminationRepository eligiblityDeterminationdDAO;

	@Autowired
	private IDCCaseRepository caseRepository;

	@Autowired
	private IApplicationRegistrationRepository citizenRepo;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public CoSummary processPendingTriggers() {
		CitizenApplicationRegistrationEntity citizenEntity = null;
		// get all pending triggers
		List<COTriggersEntity> triggerslist = coTriggerDAO.findByTriggerStatus("Pending");
		int pendingTriggers=0;
		int successTriggers=0;
		// process each pending trigger
		for (COTriggersEntity triggerEntity : triggerslist) {
			// get eligibility details based on case number
			ElibilityDetailsEntity elibilityDetailsEntity = eligiblityDeterminationdDAO
					.findByCaseNo(triggerEntity.getCaseNo());
			// get citizen data based on case number
			Optional<DCCaseEntity> optCaseEntity = caseRepository.findById(triggerEntity.getCaseNo());
			if (optCaseEntity.isPresent()) {
				DCCaseEntity caseEntity = optCaseEntity.get();
				Integer appId = caseEntity.getAppId();
				Optional<CitizenApplicationRegistrationEntity> optCitizenEntity = citizenRepo.findById(appId);
				if (optCitizenEntity.isPresent()) {
					citizenEntity = optCitizenEntity.get();
				}
			}
			// generate pdf doc having eligibility details and send that pdf soc as email 
			try {
				generatePdfAndSendMail(elibilityDetailsEntity, citizenEntity);
				successTriggers++;
			} catch (Exception e) {
				pendingTriggers++;
				e.printStackTrace();
			}
		
		}

		CoSummary summary=new CoSummary();
		summary.setTotalTriggers(triggerslist.size());
		summary.setPendingTriggers(pendingTriggers);
		summary.setSuccessTrigger(successTriggers);

		return summary;
	}

	private void generatePdfAndSendMail(ElibilityDetailsEntity eligiblityEntity,
			CitizenApplicationRegistrationEntity citizenEntity) throws Exception {
		// Set content type
		//response.setContentType("application/pdf");
		//response.setHeader("Content-Disposition", "attachment; filename=course_report.pdf");

		Document document = new Document(PageSize.A4);
			
			File file=new File(eligiblityEntity.getCaseNo()+".pdf");
			FileOutputStream fos=new FileOutputStream(file);
			
			PdfWriter.getInstance(document, fos);
			document.open();

			// Add title
			Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
			Paragraph title = new Paragraph("Plan Approval/Denial Communication", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			// Add a table
			PdfPTable table = new PdfPTable(10);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);

			// Define column widths
			float[] columnWidths = { 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f ,3.0f};
			table.setSpacingBefore(2.0f);
			table.setWidths(columnWidths);

			// Add table headers
			addTableHeader(table);

			// Add rows
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				table.addCell(String.valueOf(eligiblityEntity.getEdTracId()));
				table.addCell(String.valueOf(eligiblityEntity.getCaseNo()));
				table.addCell(String.valueOf(eligiblityEntity.getHolderName()));
				table.addCell(String.valueOf(eligiblityEntity.getHolderSSN()));
				table.addCell(String.valueOf(eligiblityEntity.getPlanName()));
				table.addCell(String.valueOf(eligiblityEntity.getPlanStatus()));
				table.addCell(String.valueOf(eligiblityEntity.getPlanStartDate().format(formatter)));
				table.addCell(String.valueOf(eligiblityEntity.getPlanEndDate().format(formatter)));
				table.addCell(String.valueOf(eligiblityEntity.getBenifitAmt()));
				table.addCell(String.valueOf(eligiblityEntity.getDenialReason()));

			document.add(table);
			document.close();
			
			// send the generated pdf doc as teh email message
			String subject="Plan Approval/Denial mail";
			String body="Hello Mr/Miss/Mrs."+citizenEntity.getFullName()+",This mail contains complete details plan approval or denial";
			emailUtils.sendMail(citizenEntity.getEmail(),subject,body,file);
			
			//Update CO_trigger table
			// store pdf doc in CO_triggers db table and update Trigger status to completed
			updateCoTrigger(eligiblityEntity.getCaseNo(),file);
			
	}

	private void updateCoTrigger(Integer caseNo, File file) throws Exception {
		//check Trigger avability based on the caseNo
		COTriggersEntity triggerEntity=coTriggerDAO.findByCaseNo(caseNo);
		//get byte[] representing pdf doc contain
		byte[] pdfContent=new byte[(int) file.length()];
		FileInputStream fis=new FileInputStream(file);
		fis.read(pdfContent);
		if(triggerEntity!=null) {
			triggerEntity.setCoNoticePdf(pdfContent);
			triggerEntity.setTriggerStatus("Completed");
			coTriggerDAO.save(triggerEntity);
		}
		fis.close();
	}

	private void addTableHeader(PdfPTable table) {
		Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
		String[] headers = { "Trace ID","CaseNo","HolderName", "HolderSSN", "PlanName", "PlanStatus", "PlanStartDate",
				"PlanEndDate", "BenifitAmt", "DenialReason"};

		for (String header : headers) {
			PdfPCell headerCell = new PdfPCell();
			headerCell.setPhrase(new Phrase(header, headerFont));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(headerCell);
		}
	}

}
