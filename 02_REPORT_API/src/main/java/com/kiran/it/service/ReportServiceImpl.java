package com.kiran.it.service;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.kiran.it.entity.EligibilityDetails;
import com.kiran.it.repository.EligibilityDetailsRepo;
import com.kiran.it.request.SearchResponse;
import com.kiran.it.search.SearchRequest;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements EligibilityService {

	@Autowired
	private EligibilityDetailsRepo aligRepo;

	@Override
	public List<String> getUniquePlanName() {
		// TODO Auto-generated method stub
		List<String> planNames = aligRepo.findByPlanNames();
		return planNames;
	}

	@Override
	public List<String> getUniquePlanStatuses() {
		// TODO Auto-generated method stub
		return aligRepo.findByPlanStatuses();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		// TODO Auto-generated method stub
		List<SearchResponse> response = new ArrayList<>();

		EligibilityDetails queryBuilder = new EligibilityDetails();

		String planName = request.getPlanName();
		if (planName != null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}

		String planStatus = request.getPlanStatus();
		if (planStatus != null && !planStatus.equals("")) {
			queryBuilder.setPlanStatus(planStatus);
		}

		LocalDate planStartDate = request.getPlanStartDate();
		if (planStartDate != null) {
			queryBuilder.setPlanStartDate(planStartDate);
		}

		LocalDate planEndDate = request.getPlanEndDate();
		if (planEndDate != null) {

			queryBuilder.setPlanEndDate(planEndDate);

		}

		Example<EligibilityDetails> example = Example.of(queryBuilder);

		List<EligibilityDetails> entities = aligRepo.findAll(example);

		for (EligibilityDetails entity : entities) {

			SearchResponse res = new SearchResponse();
			BeanUtils.copyProperties(entity, res);
			response.add(res);

		}

		return response;
	}

	@Override
	public void generateExcel(HttpServletResponse reponse) throws Exception {
		// TODO Auto-generated method stub
		List<EligibilityDetails> entities = aligRepo.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow createRow = sheet.createRow(0);

		createRow.createCell(0).setCellValue("Name");
		createRow.createCell(1).setCellValue("Mobile");
		createRow.createCell(2).setCellValue("Email");
		createRow.createCell(3).setCellValue("EligId");
		createRow.createCell(4).setCellValue("SSN");

		int i = 1;

		for (EligibilityDetails entity : entities) {

			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getName());
			dataRow.createCell(1).setCellValue(String.valueOf(entity.getMobile()));
			dataRow.createCell(2).setCellValue(entity.getEmail());
			dataRow.createCell(3).setCellValue(entity.getEligId());
			dataRow.createCell(4).setCellValue(String.valueOf(entity.getSsn()));

			i++;
		}
		
		ServletOutputStream outputStream= reponse.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
        outputStream.close();
	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<EligibilityDetails> entities = aligRepo.findAll();

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		  
        Paragraph p = new Paragraph("SEARCH REPORT", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 1.5f, 3.0f});
        table.setSpacingBefore(10);
       
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Name", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Mobile", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("eligId", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("SSN", font));
        table.addCell(cell);       
         
        
        for(EligibilityDetails entity: entities) {
        	
        	table.addCell(entity.getName());
        	table.addCell(String.valueOf(entity.getMobile()));
        	table.addCell(entity.getEmail());
        	table.addCell(String.valueOf(entity.getEligId()));
        	table.addCell(String.valueOf(entity.getSsn()));
        
        }
        
        document.add(table);
        document.add(cell);
        document.close();

	}

}
