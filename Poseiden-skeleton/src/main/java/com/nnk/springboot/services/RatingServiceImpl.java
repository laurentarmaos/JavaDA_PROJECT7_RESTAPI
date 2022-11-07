package com.nnk.springboot.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{
	
	Logger log = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
	RatingRepository ratingRepository;
	
	@Override
	public List<Rating> getRatingList() {
		
		return ratingRepository.findAll();
	}

	@Override
	public Rating getById(Integer id) {

		Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		
		return rating;
	}

	@Override
	public Rating createRating(Rating dto) {

		log.info("Service : rating created");
		return ratingRepository.save(dto);
		
	}

	@Override
	public Rating updateRating(Rating dto, Integer id) {

		dto.setId(id);
		
		log.info("Service : rating with id " + id + " updated");
		return ratingRepository.save(dto);
		
	}

	@Override
	public void deleteRating(Integer id) throws Exception {

		log.info("Service : rating with id " + id + " deleted");
		ratingRepository.deleteById(id);
		
	}

}
