package com.htc.par.model;

import java.io.Serializable;

public class ParAllocation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int parAllocId;
	private int parId;
	private int recruiterId;
	private int prescrId;
	private int candidateId;
	private String preScrdate;
	private String preScrCommnt;
	private String submitInd;
	private String submitDate;
	private String offerRecieved;
	private String exptdStartDt;
	private String actualStartDt;
	
	public ParAllocation() {
		super();
	}

	public ParAllocation(int parAllocId, int parId, int recruiterId, int prescrId, int candidateId, String preScrdate,
			String preScrCommnt, String submitInd, String submitDate, String offerRecieved, String exptdStartDt,
			String actualStartDt) {
		super();
		this.parAllocId = parAllocId;
		this.parId = parId;
		this.recruiterId = recruiterId;
		this.prescrId = prescrId;
		this.candidateId = candidateId;
		this.preScrdate = preScrdate;
		this.preScrCommnt = preScrCommnt;
		this.submitInd = submitInd;
		this.submitDate = submitDate;
		this.offerRecieved = offerRecieved;
		this.exptdStartDt = exptdStartDt;
		this.actualStartDt = actualStartDt;
	}

	public int getParAllocId() {
		return parAllocId;
	}

	public void setParAllocId(int parAllocId) {
		this.parAllocId = parAllocId;
	}

	public int getParId() {
		return parId;
	}

	public void setParId(int parId) {
		this.parId = parId;
	}

	public int getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(int recruiterId) {
		this.recruiterId = recruiterId;
	}

	public int getPrescrId() {
		return prescrId;
	}

	public void setPrescrId(int prescrId) {
		this.prescrId = prescrId;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getPreScrdate() {
		return preScrdate;
	}

	public void setPreScrdate(String preScrdate) {
		this.preScrdate = preScrdate;
	}

	public String getPreScrCommnt() {
		return preScrCommnt;
	}

	public void setPreScrCommnt(String preScrCommnt) {
		this.preScrCommnt = preScrCommnt;
	}

	public String getSubmitInd() {
		return submitInd;
	}

	public void setSubmitInd(String submitInd) {
		this.submitInd = submitInd;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getOfferRecieved() {
		return offerRecieved;
	}

	public void setOfferRecieved(String offerRecieved) {
		this.offerRecieved = offerRecieved;
	}

	public String getExptdStartDt() {
		return exptdStartDt;
	}

	public void setExptdStartDt(String exptdStartDt) {
		this.exptdStartDt = exptdStartDt;
	}

	public String getActualStartDt() {
		return actualStartDt;
	}

	public void setActualStartDt(String actualStartDt) {
		this.actualStartDt = actualStartDt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actualStartDt == null) ? 0 : actualStartDt.hashCode());
		result = prime * result + candidateId;
		result = prime * result + ((exptdStartDt == null) ? 0 : exptdStartDt.hashCode());
		result = prime * result + ((offerRecieved == null) ? 0 : offerRecieved.hashCode());
		result = prime * result + parAllocId;
		result = prime * result + parId;
		result = prime * result + ((preScrCommnt == null) ? 0 : preScrCommnt.hashCode());
		result = prime * result + ((preScrdate == null) ? 0 : preScrdate.hashCode());
		result = prime * result + prescrId;
		result = prime * result + recruiterId;
		result = prime * result + ((submitDate == null) ? 0 : submitDate.hashCode());
		result = prime * result + ((submitInd == null) ? 0 : submitInd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParAllocation other = (ParAllocation) obj;
		if (actualStartDt == null) {
			if (other.actualStartDt != null)
				return false;
		} else if (!actualStartDt.equals(other.actualStartDt))
			return false;
		if (candidateId != other.candidateId)
			return false;
		if (exptdStartDt == null) {
			if (other.exptdStartDt != null)
				return false;
		} else if (!exptdStartDt.equals(other.exptdStartDt))
			return false;
		if (offerRecieved == null) {
			if (other.offerRecieved != null)
				return false;
		} else if (!offerRecieved.equals(other.offerRecieved))
			return false;
		if (parAllocId != other.parAllocId)
			return false;
		if (parId != other.parId)
			return false;
		if (preScrCommnt == null) {
			if (other.preScrCommnt != null)
				return false;
		} else if (!preScrCommnt.equals(other.preScrCommnt))
			return false;
		if (preScrdate == null) {
			if (other.preScrdate != null)
				return false;
		} else if (!preScrdate.equals(other.preScrdate))
			return false;
		if (prescrId != other.prescrId)
			return false;
		if (recruiterId != other.recruiterId)
			return false;
		if (submitDate == null) {
			if (other.submitDate != null)
				return false;
		} else if (!submitDate.equals(other.submitDate))
			return false;
		if (submitInd == null) {
			if (other.submitInd != null)
				return false;
		} else if (!submitInd.equals(other.submitInd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParAllocation [parAllocId=" + parAllocId + ", parId=" + parId + ", recruiterId=" + recruiterId
				+ ", prescrId=" + prescrId + ", candidateId=" + candidateId + ", preScrdate=" + preScrdate
				+ ", preScrCommnt=" + preScrCommnt + ", submitInd=" + submitInd + ", submitDate=" + submitDate
				+ ", offerRecieved=" + offerRecieved + ", exptdStartDt=" + exptdStartDt + ", actualStartDt="
				+ actualStartDt + "]";
	}
	
	
	
	
	

}
