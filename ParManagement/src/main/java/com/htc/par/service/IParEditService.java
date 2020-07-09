package com.htc.par.service;

import org.springframework.stereotype.Service;

import com.htc.par.model.ParMaster;

@Service
public interface IParEditService {
	
	ParMaster getParDetails(String parno) throws Exception;
	String  updateParDetails(ParMaster parmaster) throws Exception;
	String  deleteParDetails(String parno) throws Exception;
	

}
