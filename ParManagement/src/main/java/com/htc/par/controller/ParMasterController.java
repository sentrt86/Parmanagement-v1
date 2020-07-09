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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.htc.par.exceptions.ResourceNotFoundException;
import com.htc.par.model.Area;
import com.htc.par.model.ExternalStaff;
import com.htc.par.model.Location;
import com.htc.par.model.ParMaster;
import com.htc.par.model.ParRole;
import com.htc.par.model.Skill;
import com.htc.par.service.AreaServiceImpl;
import com.htc.par.service.ExternalStaffServiceImpl;
import com.htc.par.service.LocationServiceImpl;
import com.htc.par.service.ParMasterServiceImpl;
import com.htc.par.service.ParRoleServiceImpl;
import com.htc.par.service.SkillServiceImpl;

@Controller
public class ParMasterController {

	@Autowired
	AreaServiceImpl areaServiceImpl;

	@Autowired
	SkillServiceImpl skillServiceImpl;

	@Autowired
	ParRoleServiceImpl parRoleServiceImpl;

	@Autowired
	ExternalStaffServiceImpl extStaffServiceImpl;

	@Autowired
	ParMasterServiceImpl parmasterServiceImpl;

	@Autowired
	LocationServiceImpl  locationServiceImpl;


	// Request handler for par form

	@RequestMapping(value="/parentry", method=RequestMethod.GET)
	public ModelAndView parentry(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("allAreasList", areaServiceImpl.getActiveAreas());
		modelView.addObject("allSkillsList", skillServiceImpl.getActiveSkills());
		modelView.addObject("allParRolesList", parRoleServiceImpl.getActiveParRoles());
		modelView.addObject("allExtStaffsList", extStaffServiceImpl.getActiveExtStaffs());
		modelView.addObject("allLocationsList", locationServiceImpl.getActiveLocation());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("parentry");
		return modelView;

	}

	

	// Request handler for intent to fill form

	@RequestMapping(value="/intenttofill", method=RequestMethod.GET)
	public ModelAndView intentToFill(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("intenttofill");
		return modelView;

	}

	// Request handler for candidate received form

	@RequestMapping(value="/candidatereceived", method=RequestMethod.GET)
	public ModelAndView candidateReceived(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("candidatereceived");
		return modelView;

	}



	
	  
	 


	// Request handler for prescreener result Form

	@RequestMapping(value="/prescreeningresults", method=RequestMethod.GET) 
	public ModelAndView prescreeningResult(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("prescreeningresults"); 
		return modelView;
	}


	// Request handler for submit candidate Form

	@RequestMapping(value="/submitcandidate", method=RequestMethod.GET) 
	public ModelAndView submitcandidate(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("submitcandidate"); 
		return modelView;
	}

	// Request handler for offer received Form

	@RequestMapping(value="/offerreceived", method=RequestMethod.GET) 
	public ModelAndView offerReceived(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("offerreceived"); 
		return modelView;
	}

	// Request handler for setup submitted form

	@RequestMapping(value="/setupsubmitted", method=RequestMethod.GET) 
	public ModelAndView setupSubmitted(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("setupsubmitted"); 
		return modelView;
	}


	// Request handler for candidate on board form

	@RequestMapping(value="/candidateonboard", method=RequestMethod.GET) 
	public ModelAndView candidateOnboard(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("candidateonboard"); 
		return modelView;
	}

	//Request handler to create the par


	@RequestMapping(value = "/createParMaster", method=RequestMethod.POST)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String createParMaster(@RequestBody String json, HttpServletRequest request) throws Exception {
		String data = null;
		Area area = new Area();
		Skill skill = new Skill();
		ParRole parRole = new ParRole();
		Location location = new Location();
		ExternalStaff extStaff = new ExternalStaff();
		ParMaster parmaster = new ParMaster();

		try { 

			org.json.JSONObject jsonObj = new org.json.JSONObject(json);
			area.setAreaId(jsonObj.getInt("areaId"));
			skill.setSkillId(jsonObj.getInt("skillId"));
			location.setLocationId(jsonObj.getInt("locationId"));
			extStaff.setExtStaffId(jsonObj.getInt("extStaffId"));
			parRole.setRoleId(jsonObj.getInt("roleId"));

			parmaster.setParId(jsonObj.getInt("parId"));
			parmaster.setParNumber(jsonObj.getString("parNumber"));
			parmaster.setParDescriptionText(jsonObj.getString("parDescriptionText"));
			parmaster.setParStatus(jsonObj.getString("parStatus"));
			parmaster.setParReceivedDate(jsonObj.getString("parReceivedDate"));
			parmaster.setEmailSent(jsonObj.getString("emailSent"));
			parmaster.setIntentSentDate(jsonObj.getString("intentSentDate"));
			parmaster.setIntentToFill(jsonObj.getString("intentToFill"));
			parmaster.setParComment(jsonObj.getString("parComment"));
			parmaster.setArea(area);
			parmaster.setSkill(skill);
			parmaster.setLocation(location);
			parmaster.setExternalStaff(extStaff);
			parmaster.setParRole(parRole);
			data = parmasterServiceImpl.createParMaster(parmaster);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return data;


	}
	
	// Request handler to update the intent to fill
	
	@RequestMapping(value = "/updateIntentToFill", method=RequestMethod.POST)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateIntentToFill(@RequestBody String json, HttpServletRequest request) throws Exception {
		String data = null;
	
		ParMaster parmaster = new ParMaster();

		try { 

			org.json.JSONObject jsonObj = new org.json.JSONObject(json);
			parmaster.setParId(jsonObj.getInt("parId"));
			parmaster.setParNumber(jsonObj.getString("parNumber"));
			parmaster.setIntentSentDate(jsonObj.getString("intentSentDate"));
			parmaster.setIntentToFill(jsonObj.getString("intentToFill"));
			data = parmasterServiceImpl.updateIntentToFill(parmaster);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return data;


	}
	
	// Request handler to update the intent to fill
	
		@RequestMapping(value = "/getParMasterByParNum/{parNum}", method=RequestMethod.GET)
		@Consumes(MediaType.TEXT_PLAIN)
		@Produces(MediaType.TEXT_PLAIN)
		public @ResponseBody List<ParMaster> getParMasterByParNum(@PathVariable("parNum") String parNum) throws Exception {
			List<ParMaster> parMaster = null;
			parMaster = parmasterServiceImpl.getParMasterByParNum(parNum);
			return parMaster;

		}


	//Request handler to get the next par sequence 

	@RequestMapping(value="/getNextParSeqId", method=RequestMethod.GET) 
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody int getNextParSeqId() throws Exception { 			
		return parmasterServiceImpl.getNextParSeqId();	
	}


}
