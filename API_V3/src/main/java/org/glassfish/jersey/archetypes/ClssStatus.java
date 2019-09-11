package org.glassfish.jersey.archetypes;

public class ClssStatus {
	private boolean success;
    private String description;
    private String status;
    
	public boolean isSuccess() {
		return success;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
