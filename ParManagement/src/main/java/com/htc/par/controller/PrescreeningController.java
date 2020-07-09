package com.htc.par.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.Area;
import com.htc.par.model.Candidate;
import com.htc.par.model.ParAllocation;
import com.htc.par.model.ParMaster;
import com.htc.par.model.Prescreener;
import com.htc.par.model.Prescreening;
import com.htc.par.service.AreaServiceImpl;
import com.htc.par.service.CandidateServiceImpl;
import com.htc.par.service.ExternalStaffServiceImpl;
import com.htc.par.service.LocationServiceImpl;
import com.htc.par.service.ParEditServiceImpl;
import com.htc.par.service.ParRoleServiceImpl;
import com.htc.par.service.PreScreenerServiceImpl;
import com.htc.par.service.PreScreeningServiceImpl;
import com.htc.par.service.SkillServiceImpl;


@Controller
public class PrescreeningController {
	
	
	
	@Autowired
	PreScreeningServiceImpl prescreeningServiceImpl;
	
	@Autowired ParEditServiceImpl pareditserviceImpl;

	@Autowired
	SkillServiceImpl skillServiceImpl;

	@Autowired
	ParRoleServiceImpl parRoleServiceImpl;

	@Autowired
	ExternalStaffServiceImpl extStaffServiceImpl;
	
	@Autowired
	PreScreenerServiceImpl preScreenerServiceImpl;
	
	
	
	
	// Request handler for prescreener form
	  
	@RequestMapping(value="/prescreening", method=RequestMethod.GET) public
	ModelAndView prescreening(Locale locale,Model model) throws Exception{
	ModelAndView modelView = new ModelAndView();
	System.out.println("prescreening controller methods");
	//modelView.addObject("allcandidateList",candidateServiceImpl.getAllCandidates());
	modelView.addObject("username",HomeController.username);
	modelView.setViewName("prescreening"); 
	return modelView; 
	}

	
	
	@RequestMapping(value="/prescreening/{parNo}",method=RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public @ResponseBody  Prescreening getPrescreeningDetails(@PathVariable("parNo") String parNo,HttpServletRequest request) throws Exception {
		System.out.println("first prescreening controller controller invoked");
		System.out.println("parNo"+parNo);
		String parNumber=parNo;
		return getPrescreening(parNumber);
	}
	
	@RequestMapping(value="/updatePrescreening",method=RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public @ResponseBody Prescreening UpdatePrescreeningDetails(@RequestBody String json,HttpServletRequest request) throws Exception { 	
		String parNumber=null;
		boolean dataUpdated;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			ParAllocation parallocation = mapper.readValue(json,ParAllocation.class);
			System.out.println("First prescreening controller update method invoked");
			System.out.println(parallocation);
			dataUpdated = prescreeningServiceImpl.updateParAllocation(parallocation);
			if (dataUpdated)
			{	
			 System.out.println("call before get parnumber for parId");	
			 System.out.println(parallocation.getParId());
			  parNumber = prescreeningServiceImpl.getParNumberforParId(parallocation.getParId());
			  System.out.println("call after get parnumber for parId"+parNumber);	
			  return getPrescreening(parNumber);
			}
			return null;
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return null;
	}
	
	public Prescreening getPrescreening(String parno) throws Exception {
		Prescreening prescreening = new Prescreening();
		ParMaster parmaster =pareditserviceImpl.getParDetails(parno);
		System.out.println("After parmaster service call");
		System.out.println(parmaster);
		
		
		List<Prescreener> preScreenerList = preScreenerServiceImpl.getListAllPreScreener();
		System.out.println("after prescsreeninglist");
		System.out.println(preScreenerList);
		
		List<Candidate> candidateList = prescreeningServiceImpl.getAllCandidateforParNo(parmaster.getParId());
		
		System.out.println("after candidatlist");
		List<ParAllocation> parAllocations = prescreeningServiceImpl.getParAllocationbyParNo(parmaster.getParId());
		
		System.out.println("after parallocation list");
		prescreening.setCandidateList(candidateList);
		prescreening.setParAllocationList(parAllocations);
		prescreening.setParmaster(parmaster);
		prescreening.setPreScreenerList(preScreenerList);
		System.out.println("last of prescreening method");
		System.out.println(prescreening);
		return prescreening;
	}

}
