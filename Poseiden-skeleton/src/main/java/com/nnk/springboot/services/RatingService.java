package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Rating;

public interface RatingService {
	
	List<Rating> getRatingList();
	
	Rating getById(Integer id);
	
	Rating createRating(Rating dto);
	
	Rating updateRating(Rating dto, Integer id);
	
	void deleteRating(Integer id) throws Exception;

}
