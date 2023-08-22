package com.kiran.it.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kiran.it.request.SearchResponse;
import com.kiran.it.search.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

@Service
public interface EligibilityService {
	
	public List<String> getUniquePlanName();
	
	public List<String> getUniquePlanStatuses();
	
	List<SearchResponse> search(SearchRequest request);
	
	public void generatePdf(HttpServletResponse reponse) throws Exception;
	
	public void generateExcel(HttpServletResponse response) throws Exception;
	
	

}
