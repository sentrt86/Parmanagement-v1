package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.Prescreener;
import com.htc.par.model.ResponseException;

@Service
public class PreScreenerServiceImpl implements IPreScreenerService {
	
	
	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<Prescreener> getListAllPreScreener() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/prescreener/getPrescreeners";
		try {
			ResponseEntity<List<Prescreener>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Prescreener>>() {});
			List<Prescreener> allPrescreeners = response.getBody();
			  for(Prescreener prescreener: allPrescreeners) {
				  prescreener.setPreScreenerActive(prescreener.getPreScreenerActive().equalsIgnoreCase("true") ? "Yes" : "No");
			  }
			  return allPrescreeners;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
		
		
	}

	
	@Override
	public String addPrescreener(Prescreener prescreener) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/prescreener/createPrescreener";
		prescreener.setPreScreenerActive(prescreener.getPreScreenerActive().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Prescreener> request = new HttpEntity<>(prescreener);
		try { 
			ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
		} 
		return responseException.getMessage();
	}

	@Override
	public String updatePrescreener(Prescreener prescreener) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/prescreener/updatePrescreener";
		prescreener.setPreScreenerActive(prescreener.getPreScreenerActive().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Prescreener> request = new HttpEntity<>(prescreener);
		try {
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});		
		return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
		}
		
		return responseException.getMessage();
	}

	@Override
	public String deletePrescreener(int prescreenerId) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/prescreener/deletePrescreener/"+prescreenerId ;
		HttpEntity<Integer> request = new HttpEntity<>(prescreenerId);
		try {
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});		
		return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
		}
		
		return responseException.getMessage();

	}


	@Override
	public int getnextPrescreenerID() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/prescreener/getNextPrescreenerId";
		try {
			ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());	
		}
	}

}