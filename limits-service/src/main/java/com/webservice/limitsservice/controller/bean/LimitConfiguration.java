package com.webservice.limitsservice.controller.bean;

public class LimitConfiguration {

	private int maxLimit;
	private int minLimit;
	
	public LimitConfiguration() {
	}
	
	public LimitConfiguration(int maxLimit, int minLimit) {
		this.maxLimit = maxLimit;
		this.minLimit = minLimit;
	}
	
	public int getMaxLimit() {
		return maxLimit;
	}
	public void setMaxLimit(int maxLimit) {
		this.maxLimit = maxLimit;
	}
	public int getMinLimit() {
		return minLimit;
	}
	public void setMinLimit(int minLimit) {
		this.minLimit = minLimit;
	}
	
	
}
