package com.example.demo.model;

public class CheckCondition {

	int start;
	int end;
	int domain;
	int updatedDomain;
	
	
	public CheckCondition() {
	
	}
	
	
	public CheckCondition(int start, int end, int domain, int updatedDomain) {
		super();
		this.start = start;
		this.end = end;
		this.domain = domain;
		this.updatedDomain = updatedDomain;
	}


	public int getDomain() {
		return domain;
	}


	public void setDomain(int domain) {
		this.domain = domain;
	}


	public int getUpdatedDomain() {
		return updatedDomain;
	}


	public void setUpdatedDomain(int updatedDomain) {
		this.updatedDomain = updatedDomain;
	}


	public CheckCondition(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
}
