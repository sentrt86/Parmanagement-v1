package com.htc.par.controller;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.Area;
import com.htc.par.model.ExternalStaff;
import com.htc.par.model.Location;
import com.htc.par.model.ParMaster;
import com.htc.par.model.ParRole;
import com.htc.par.model.Prescreener;
import com.htc.par.model.Skill;
import com.htc.par.service.AreaServiceImpl;
import com.htc.par.service.ExternalStaffServiceImpl;
import com.htc.par.service.LocationServiceImpl;
import com.htc.par.service.ParEditServiceImpl;
import com.htc.par.service.ParMasterServiceImpl;
import com.htc.par.service.ParRoleServiceImpl;
import com.htc.par.service.SkillServiceImpl;

@Controller
public class ParEditController {
	
@Autowired ParEditServiceImpl pareditserviceImpl;

@Autowired
AreaServiceImpl areaServiceImpl;

@Autowired
SkillServiceImpl skillServiceImpl;

@Autowired
ParRoleServiceImpl parRoleServiceImpl;

@Autowired
ExternalStaffServiceImpl extStaffServiceImpl;

@Autowired
LocationServiceImpl  locationServiceImpl;
	
	
@RequestMapping("/paredit")
public ModelAndView parEditScreen(Locale locale,Model model) throws Exception {
	ModelAndView modelandview = new ModelAndView();
	modelandview.addObject("allAreasList", areaServiceImpl.getActiveAreas());
	modelandview.addObject("allSkillsList", skillServiceImpl.getActiveSkills());
	modelandview.addObject("allParRolesList", parRoleServiceImpl.getActiveParRoles());
	modelandview.addObject("allExtStaffsList", extStaffServiceImpl.getActiveExtStaffs());
	modelandview.addObject("allLocationsList", locationServiceImpl.getActiveLocation());
	modelandview.addObject("username",HomeController.username);	
	modelandview.setViewName("paredit");
	return modelandview;
	
}

@RequestMapping(value="/getPardetails/{parNo}",method=RequestMethod.GET)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public @ResponseBody  ParMaster getParDetails(@PathVariable("parNo") String parNo,HttpServletRequest request) throws Exception {
	System.out.println("first controller invoked");
	ModelAndView modelView  = new ModelAndView();
	modelView.addObject("allAreasList", areaServiceImpl.getActiveAreas());
	modelView.addObject("allSkillsList", skillServiceImpl.getActiveSkills());
	modelView.addObject("allParRolesList", parRoleServiceImpl.getActiveParRoles());
	modelView.addObject("allExtStaffsList", extStaffServiceImpl.getActiveExtStaffs());
	modelView.addObject("allLocationsList", locationServiceImpl.getActiveLocation());
	modelView.addObject("username",HomeController.username);	
	modelView.setViewName("paredit");
	return pareditserviceImpl.getParDetails(parNo);
}

@RequestMapping(value="/updatePardetails",method=RequestMethod.POST)
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public @ResponseBody String updateParDetails(@RequestBody String json,HttpServletRequest request) throws Exception { 		
	System.out.println("first controller invoked");
	 	
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
		data = pareditserviceImpl.updateParDetails(parmaster);

	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}

	return data;

}

@RequestMapping(value="/deletePardetails/{parNo}",method=RequestMethod.POST)
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public @ResponseBody  String deleteParDetails(@PathVariable("parNo") String parNo,HttpServletRequest request) throws Exception {
	System.out.println("first controller invoked deletefunc");
	 	
	return pareditserviceImpl.deleteParDetails(parNo);
}
}
