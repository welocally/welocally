package com.sightlyinc.ratecred.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *   `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `version` INT(11) NOT NULL ,
  `patron_id` BIGINT(20) NOT NULL ,
  `place_id` BIGINT(20) NOT NULL ,
  `type` VARCHAR(255) NULL DEFAULT NULL ,
  `notes` LONGTEXT NULL DEFAULT NULL ,
  `twitter_status_id` BIGINT(20) NULL DEFAULT NULL ,
  `patron_rating` FLOAT(11) NULL DEFAULT NULL ,
  `user_rating` FLOAT(11) NULL DEFAULT NULL ,
  `flag` VARCHAR(16) NULL DEFAULT 'ACTIVE' ,
  `referral_url` VARCHAR(1024) NULL DEFAULT NULL ,
  `referral_token` VARCHAR(255) NULL DEFAULT NULL ,
  `checkin_foursquare` VARCHAR(255) NULL DEFAULT NULL ,
  `checkin_gowalla` VARCHAR(255) NULL DEFAULT NULL ,
  `txid_foursquare` VARCHAR(45) NULL DEFAULT NULL ,
  `txid_gowalla` VARCHAR(45) NULL DEFAULT NULL ,
  `time_created` BIGINT NULL DEFAULT NULL ,
  `time_updated` BIGINT NULL DEFAULT NULL ,
 * @author claygraham
 *
 */
public class Rating extends BaseEntity implements Serializable {
	
	
	@JsonProperty
	private String type;
	
	@JsonProperty
	private String notes;
	
	
	@JsonProperty
	private Long twitterStatusId;
	
	
	@JsonProperty
	private Float raterRating;
	
	@JsonProperty
	private Float userRating;
	
	@JsonProperty
	private String referalUrl;
	
	@JsonProperty
	private String referalToken;
	
	@JsonProperty
	private String checkedinFoursquare;
	
	@JsonProperty
	private String txIdFoursquare;
	
	@JsonProperty
	private String checkedinGowalla;
	
	@JsonProperty
	private String txIdGowalla;

	@JsonProperty
	private transient Set<RatingAttribute> attributes = new HashSet<RatingAttribute>();

	
	@JsonProperty
	private transient Set<Compliment> compliments;
	
	@JsonProperty
	private transient Patron owner;
	
	@JsonProperty
	private transient Place place;
	


	@JsonProperty
	public Float getRaterRating() {
		return raterRating;
	}

	@JsonProperty
	public void setRaterRating(Float raterRating) {
		this.raterRating = raterRating;
	}

	@JsonProperty
	public Float getUserRating() {
		return userRating;
	}

	@JsonProperty
	public void setUserRating(Float userRating) {
		this.userRating = userRating;
	}

	@JsonProperty
	public String getNotes() {
		return notes;
	}

	@JsonProperty
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@JsonProperty
	public Patron getOwner() {
		return owner;
	}

	@JsonProperty
	public void setOwner(Patron owner) {
		this.owner = owner;
	}



	@JsonProperty
	public Place getPlace() {
		return place;
	}

	@JsonProperty
	public void setPlace(Place place) {
		this.place = place;
	}



	@JsonProperty
	public String getType() {
		return type;
	}

	@JsonProperty
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty
	public Set<RatingAttribute> getAttributes() {
		return attributes;
	}

	@JsonProperty
	public void setAttributes(Set<RatingAttribute> attributes) {
		this.attributes = attributes;
	}
		

	@JsonIgnore
	public Set<Compliment> getCompliments() {
		return compliments;
	}

	@JsonIgnore
	public void setCompliments(Set<Compliment> compliments) {
		this.compliments = compliments;
	}

	@JsonProperty
	public Long getTwitterStatusId() {
		return twitterStatusId;
	}

	@JsonProperty
	public void setTwitterStatusId(Long twitterStatusId) {
		this.twitterStatusId = twitterStatusId;
	}

	@JsonProperty
	public String getReferalUrl() {
		return referalUrl;
	}

	@JsonProperty
	public void setReferalUrl(String referalUrl) {
		this.referalUrl = referalUrl;
	}

	@JsonProperty
	public String getReferalToken() {
		return referalToken;
	}

	@JsonProperty
	public void setReferalToken(String referalToken) {
		this.referalToken = referalToken;
	}

	@JsonProperty
	public String getCheckedinFoursquare() {
		return checkedinFoursquare;
	}

	@JsonProperty
	public void setCheckedinFoursquare(String checkedinFoursquare) {
		this.checkedinFoursquare = checkedinFoursquare;
	}

	@JsonProperty
	public String getCheckedinGowalla() {
		return checkedinGowalla;
	}

	@JsonProperty
	public void setCheckedinGowalla(String checkedinGowalla) {
		this.checkedinGowalla = checkedinGowalla;
	}

	@JsonProperty
	public String getTxIdFoursquare() {
		return txIdFoursquare;
	}

	@JsonProperty
	public void setTxIdFoursquare(String txIdFoursquare) {
		this.txIdFoursquare = txIdFoursquare;
	}

	@JsonProperty
	public String getTxIdGowalla() {
		return txIdGowalla;
	}

	@JsonProperty
	public void setTxIdGowalla(String txIdGowalla) {
		this.txIdGowalla = txIdGowalla;
	}

	/*@Override
	public String toString() {
		return "Rating [attributes=" + attributes + ", checkedinFoursquare="
				+ checkedinFoursquare + ", checkedinGowalla="
				+ checkedinGowalla + ", compliments=" + compliments + ", id="
				+ id + ", notes=" + notes + ", owner=" + owner + ", place="
				+ place + ", raterRating=" + raterRating + ", referalToken="
				+ referalToken + ", referalUrl=" + referalUrl
				+ ", timeCreated=" + timeCreated + ", timeCreatedGmt="
				+ timeCreatedGmt + ", timeCreatedMills=" + timeCreatedMills
				+ ", twitterStatusId=" + twitterStatusId + ", type=" + type
				+ ", userRating=" + userRating + ", version=" + version + "]";
	}*/


	



}
