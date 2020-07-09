package com.htc.par.service;

import java.util.List;

import com.htc.par.model.Candidate;
import com.htc.par.model.ParAllocation;
import com.htc.par.model.Prescreening;

public interface IPrescreeningService {
	
	public List<ParAllocation> getParAllocationbyParNo(int parNo) throws Exception;
	public List<Candidate> getAllCandidateforParNo(int parNo) throws Exception;
	public boolean updateParAllocation(ParAllocation parallocation) throws Exception;
	public String deleteCandidateonParAllocation(int parallocId) throws Exception;
	public String getParNumberforParId(int parId) throws Exception;
	
}
