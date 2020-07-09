package com.htc.par.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.htc.par.model.ParMaster;
import com.htc.par.model.Recruiter;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailRecruiterService {
	
	@Autowired JavaMailSender sendEmail;
	
	@Autowired
	RecruiterServiceImpl recruiterServiceImpl;
	
	@Autowired
	ParMasterServiceImpl parMasterServiceImpl;
	
	@Autowired
    private Configuration freemarkerConfig;
	
	
	@Autowired
	public  EmailRecruiterService(JavaMailSender javaMailSender) {
		this.sendEmail = javaMailSender;
	}
	
	
	public void sendEmail(ParMaster Parmaster) throws Exception,MailException {
		
			System.out.println("Send email method in service");
		
		 	MimeMessage message = sendEmail.createMimeMessage();
		 
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	        
	        System.out.println("After MimeMessage and MimeMessageHelper");
	        
	        Map<String, Object> model = new HashMap<String, Object>();
	        model.put("areaName", Parmaster.getArea().getAreaName());
	        model.put("roleName", Parmaster.getParRole().getRoleName());
	        model.put("locationName", Parmaster.getLocation().getLocationName());
	        model.put("skillName", Parmaster.getSkill().getSkillName());
	        model.put("parNum", Parmaster.getParNumber());
	        model.put("extstaffName", Parmaster.getExternalStaff().getExtStaffName());
	        model.put("parRecievedDate",Parmaster.getParReceivedDate());
	        model.put("parStatus", Parmaster.getParStatus());
	        model.put("intenttoFilldate", Parmaster.getIntentSentDate());
	        model.put("parComment", Parmaster.getParComment());
	      
	        // Using a subfolder such as /templates here
	        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
	        
	        System.out.println("Freemarker template");
	        
	        
	        int maillistCount=0;
	        
	        Template t = freemarkerConfig.getTemplate("email-template.ftl");
	        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
	        List<Recruiter> recruiterList = recruiterServiceImpl.getAllRecruiters();
	        String mailList[] = new String[recruiterList.size()];
	        for (Recruiter recruiter : recruiterList) {
	        	mailList[maillistCount]=recruiter.getRecruiterEmail();
	        	maillistCount++;
			}
	 
	        helper.setTo(mailList);
	        helper.setFrom("senthilkumar.ulaganathan@htcinc.com");
	        helper.setText(text, true);
	        helper.setSubject("New par Allocation");
	        try {
	        System.out.println("before before before mail send");
	        sendEmail.send(message);
	        System.out.println("After after after mail send");
		
	        Parmaster.setEmailSent("Yes");
	        parMasterServiceImpl.updateEmailRecruiters(Parmaster);
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	}

}
