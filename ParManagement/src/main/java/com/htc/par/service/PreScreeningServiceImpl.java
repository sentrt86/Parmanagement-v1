package com.htc.par.service;

import java.util.ArrayList;
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
import com.htc.par.model.Area;
import com.htc.par.model.Candidate;
import com.htc.par.model.ParAllocation;
import com.htc.par.model.ParMaster;
import com.htc.par.model.Prescreener;
import com.htc.par.model.Prescreening;
import com.htc.par.model.ResponseException;

@Service
public class PreScreeningServiceImpl implements IPrescreeningService{
	
	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CandidateServiceImpl candidateServiceImpl;
	
	
	

	@Override
	public boolean updateParAllocation(ParAllocation parallocation) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/prescreening/updatePrescreening";
		System.out.println("prescreening first service update method");
		System.out.println(parallocation);
		
		HttpEntity<ParAllocation> request = new HttpEntity<>(parallocation);
		try {
			System.out.println("Try method in first service prescreening");
			ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<Boolean>() {});		
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
		}

		return false;
	}

	@Override
	public String deleteCandidateonParAllocation(int parallocId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParAllocation> getParAllocationbyParNo(int parNo) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/prescreening/getParallocations/"+parNo;
		try {
			ResponseEntity<List<ParAllocation>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ParAllocation>>() {});
			List<ParAllocation> parAllocations = response.getBody();
			return parAllocations;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
		
	}

	@Override
	public List<Candidate> getAllCandidateforParNo(int parNo) throws Exception {
		List<ParAllocation> parAllocationList= this.getParAllocationbyParNo(parNo);
		System.out.println(parAllocationList);
		System.out.println(parAllocationList.size());
		List<Candidate> candidateList = new ArrayList<Candidate>();
		List<Candidate> candidateListforparNo = new ArrayList<Candidate>();
		
		int[] candidateIdList= new int[parAllocationList.size()];
		int candidateCounter=0;
		for (ParAllocation parAllocation : parAllocationList) {
			candidateIdList[candidateCounter] = parAllocation.getCandidateId();
			candidateCounter++;
		}
		
		candidateList = candidateServiceImpl.getAllCandidates();
		System.out.println("CandidateCntr:"+candidateCounter);
		for(int i=0;i<=candidateCounter-1;i++)
		{
			for(Candidate candidate:candidateList)
			{
				if(candidateIdList[i]==candidate.getCandidateId())
				{
					candidateListforparNo.add(candidate);
				}
			}
		}
		System.out.println(candidateListforparNo);
		
		return candidateListforparNo;
	}

	@Override
	public String getParNumberforParId(int parId) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/prescreening/getParnumberforparId/"+parId;
		try {
			System.out.println("first service method called for getparnumber");
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
			return response.getBody();
			
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	}

	
	
}
