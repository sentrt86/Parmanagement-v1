package com.htc.par.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.htc.par.model.Area;
import com.htc.par.model.ExternalStaff;
import com.htc.par.model.Location;
import com.htc.par.model.ParMaster;
import com.htc.par.model.ParRole;
import com.htc.par.model.Skill;
import com.htc.par.service.AreaServiceImpl;
import com.htc.par.service.EmailRecruiterService;
import com.htc.par.service.ExternalStaffServiceImpl;
import com.htc.par.service.LocationServiceImpl;
import com.htc.par.service.ParEditServiceImpl;
import com.htc.par.service.ParRoleServiceImpl;
import com.htc.par.service.SkillServiceImpl;

@Controller
public class EmailRecruiterController {
	
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
	
	@Autowired
	EmailRecruiterService emailRecruiterService;
	
	@Autowired
	ParEditServiceImpl pareditserviceimpl;
	
	// Request handler for email recruiters form

		@RequestMapping(value="/emailrecruiters", method=RequestMethod.GET)
		public ModelAndView emailRecruiters(Locale locale,Model model) throws Exception {
			ModelAndView modelView  = new ModelAndView();
			modelView.addObject("username",HomeController.username);
			modelView.setViewName("emailrecruiters");
			return modelView;

		}
		
		
		@RequestMapping(value="/emailrecruiters/getPardetails/{parNo}",method=RequestMethod.GET)
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.TEXT_PLAIN)
		public @ResponseBody  ParMaster getParDetails(@PathVariable("parNo") String parNo,HttpServletRequest request) throws Exception {
			System.out.println("first Email recruiter controller invoked");
			ModelAndView modelView  = new ModelAndView();
			modelView.addObject("allAreasList", areaServiceImpl.getActiveAreas());
			modelView.addObject("allSkillsList", skillServiceImpl.getActiveSkills());
			modelView.addObject("allParRolesList", parRoleServiceImpl.getActiveParRoles());
			modelView.addObject("allExtStaffsList", extStaffServiceImpl.getActiveExtStaffs());
			modelView.addObject("allLocationsList", locationServiceImpl.getActiveLocation());
			modelView.addObject("username",HomeController.username);	
			modelView.setViewName("emailrecruiters");
			return pareditserviceImpl.getParDetails(parNo);
		}
		
		@RequestMapping(value="/sendEmail",method=RequestMethod.POST)
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody String sendEmail(@RequestBody String json,HttpServletRequest request) throws Exception { 		
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
				area.setAreaName(jsonObj.getString("areaName"));
				skill.setSkillName(jsonObj.getString("skillName"));
				location.setLocationName(jsonObj.getString("locationName"));
				extStaff.setExtStaffName(jsonObj.getString("extStaffName"));
				parRole.setRoleName(jsonObj.getString("roleName"));

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
				System.out.println("before the email send method");
				emailRecruiterService.sendEmail(parmaster);
				

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			catch (MailException mailException) {
				System.out.println("mail exception caught");
				System.out.println(mailException);
				mailException.printStackTrace();
			}
			return "Congratulations! Your mail has been send to the user.";

			

		}

		
		

}
