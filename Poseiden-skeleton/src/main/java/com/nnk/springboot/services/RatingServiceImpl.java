package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	RatingRepository ratingRepository;
	
	@Override
	public List<Rating> getRatingList() {
		
		return ratingRepository.findAll();
	}

	@Override
	public Rating getById(Integer id) {

		Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		
		return rating;
	}

	@Override
	public void createRating(Rating dto) {

		ratingRepository.save(dto);
		
	}

	@Override
	public void updateRating(Rating dto, Integer id) {

		dto.setId(id);
		
		ratingRepository.save(dto);
		
	}

	@Override
	public void deleteRating(Integer id) throws Exception {

		ratingRepository.deleteById(id);
		
	}

}
