package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
    @Column(name="moodysRating")
	private String moodysRating;
	
    @Column(name="sandPRating")
	private String sandPRating;
	
    @Column(name="fitchRating")
	private String fitchRating;
	
    @Column(name="orderNumber")
	private Integer orderNumber;
	
	public Rating() {}
	
	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		super();
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}
	
	public Rating(Integer id, String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		super();
		this.id = id;
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoodysRating() {
		return moodysRating;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	public String getSandPRating() {
		return sandPRating;
	}

	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
}
