package com.sightlyinc.ratecred.model;

public class RaterMetrics {
	private Long id;
	private String username;	
	private String status;
	private Integer ratings;
	private Integer received;
	private Integer given;
	private Long score;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
	
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getRatings() {
		return ratings;
	}
	public void setRatings(Integer ratings) {
		this.ratings = ratings;
	}
	public Integer getReceived() {
		return received;
	}
	public void setReceived(Integer received) {
		this.received = received;
	}
	public Integer getGiven() {
		return given;
	}
	public void setGiven(Integer given) {
		this.given = given;
	}

	
	

}
