package com.sightlyinc.ratecred.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.noi.utility.string.StringUtils;

public class Rating {
	private Long id;
	private Integer version = new Integer(0);
	private String type;
	private String notes;
	private String city;
	private String state;
	private String placeInfo;
	private Date timeCreated;
	private Long timeCreatedMills;
	private String timeCreatedGmt;	
	private Float raterRating;
	private Float userRating;
	private Float billTotal;
	private Float tipRatio;
	private Integer numberSplits;

	
	// relations
	//private Set<Timer> timers;
	private Set<RatingAttribute> attributes;
	
	private Set<Compliment> compliments;
	
	private Rater owner;
	
	private Place place;
	

	public Long getTimeCreatedMills() {
		return timeCreatedMills;
	}

	public void setTimeCreatedMills(Long timeCreatedMills) {
		this.timeCreatedMills = timeCreatedMills;
	}

	public String getTimeCreatedGmt() {
		return timeCreatedGmt;
	}

	public void setTimeCreatedGmt(String timeCreatedGmt) {
		this.timeCreatedGmt = timeCreatedGmt;
	}

	public Float getRaterRating() {
		return raterRating;
	}

	public void setRaterRating(Float raterRating) {
		this.raterRating = raterRating;
	}

	public Float getUserRating() {
		return userRating;
	}

	public void setUserRating(Float userRating) {
		this.userRating = userRating;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Rater getOwner() {
		return owner;
	}

	public void setOwner(Rater owner) {
		this.owner = owner;
	}


	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Set<RatingAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<RatingAttribute> attributes) {
		this.attributes = attributes;
	}
		

	public Set<Compliment> getCompliments() {
		return compliments;
	}

	public void setCompliments(Set<Compliment> compliments) {
		this.compliments = compliments;
	}

	/*@Override
	public String toString() {

		StringBuffer buf = new StringBuffer();

		try {
			buf.append("[");

			Map<String, String> description = BeanUtils.describe(this);

			for (String key : description.keySet()) {
				buf.append("[");
				buf.append(key);
				buf.append("=");

				if (description.get(key) != null
						&& StringUtils.isNotEmpty(description.get(key)
								.toString()))
					buf.append(description.get(key).toString());

				buf.append("]");
			}
			buf.append("]");

		} catch (IllegalAccessException e) {

		} catch (InvocationTargetException e) {

		} catch (NoSuchMethodException e) {

		}

		return buf.toString();
	}*/

}
