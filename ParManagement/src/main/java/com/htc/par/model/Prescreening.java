package com.htc.par.model;

import java.util.List;

public class Prescreening {
	
	private ParMaster parmaster;
	private List<Candidate> candidateList;
	private List<ParAllocation> parAllocationList;
	private List<Prescreener> preScreenerList;
	
	public Prescreening() {
		super();
	}

	public Prescreening(ParMaster parmaster, List<Candidate> candidateList, List<ParAllocation> parAllocationList, List<Prescreener> preScreenerList) {
		super();
		this.parmaster = parmaster;
		this.candidateList = candidateList;
		this.parAllocationList = parAllocationList;
		this.setPreScreenerList(preScreenerList);
	}

	public ParMaster getParmaster() {
		return parmaster;
	}

	public void setParmaster(ParMaster parmaster) {
		this.parmaster = parmaster;
	}

	public List<Candidate> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}

	public List<ParAllocation> getParAllocationList() {
		return parAllocationList;
	}

	public void setParAllocationList(List<ParAllocation> parAllocationList) {
		this.parAllocationList = parAllocationList;
	}

	

	public List<Prescreener> getPreScreenerList() {
		return preScreenerList;
	}

	public void setPreScreenerList(List<Prescreener> preScreenerList) {
		this.preScreenerList = preScreenerList;
	}

	@Override
	public String toString() {
		return "Prescreening [parmaster=" + parmaster + ", candidateList=" + candidateList + ", parAllocationList="
				+ parAllocationList + ", preScreenerList=" + preScreenerList + "]";
	}
	
	
	
	

}
