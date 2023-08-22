package com.kiran.it.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kiran.it.request.SearchResponse;
import com.kiran.it.search.SearchRequest;
import com.kiran.it.service.EligibilityService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController {

	@Autowired
	private EligibilityService service;

	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanNames() {

		List<String> planName = service.getUniquePlanName();

		return new ResponseEntity<List<String>>(planName, HttpStatus.OK);
	}

	@GetMapping("statuses")
	public ResponseEntity<List<String>> getPlanStatus() {

		List<String> planStatuses = service.getUniquePlanStatuses();

		return new ResponseEntity<List<String>>(planStatuses, HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request) {

		List<SearchResponse> search = service.search(request);

		return new ResponseEntity<List<SearchResponse>>(search, HttpStatus.OK);

	}

	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";

		String headerValue = "attachment;filename=data.xls";
		response.setHeader(headerKey, headerValue);

		service.generateExcel(response);

	}

	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception {

		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename-date.pdf";

		response.setHeader(headerKey, headerValue);

		service.generatePdf(response);
	}

}
