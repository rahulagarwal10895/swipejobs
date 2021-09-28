package com.swipejobs.matchjob.model;

public class JobSearchAddress extends Location{
	
	private String unit;
	private int maxJobDistance;
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getMaxJobDistance() {
		return maxJobDistance;
	}
	public void setMaxJobDistance(int maxJobDistance) {
		this.maxJobDistance = maxJobDistance;
	}

	

}
