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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.ParMaster;
import com.htc.par.model.Prescreener;
import com.htc.par.model.ResponseException;

@Service
public class ParEditServiceImpl implements IParEditService {
	
	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public ParMaster getParDetails(String parno) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/paredit/getPardetails/"+parno;
		System.out.println("first service invoked");
		HttpEntity<String> request = new HttpEntity<String>(parno);
		try {
			System.out.println("parno"+parno);
			System.out.println("parno"+parno);
			System.out.println("parno"+parno);
			System.out.println("parno"+parno);
			
			ResponseEntity<ParMaster> response = restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<ParMaster>() {});
			System.out.println("After call");
			System.out.println("parno"+parno);
			ParMaster parmaster = response.getBody();
			return parmaster;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	}

	@Override
	public String updateParDetails(ParMaster parmaster) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/paredit/updatePardetails";
		System.out.println("first service invoked");
		
		HttpEntity<ParMaster> request = new HttpEntity<>(parmaster);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<String>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
		}
		return responseException.getMessage();
	}

	@Override
	public String deleteParDetails(String parno) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/paredit/deletePardetails/"+parno;
		System.out.println("first service invoked deletefunc");
		HttpEntity<String> request = new HttpEntity<>(parno);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<String>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
		}
		return responseException.getMessage();
	}
	

}
