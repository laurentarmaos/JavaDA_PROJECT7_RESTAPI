package com.nnk.springboot.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTests {
	
	@Mock
	private RatingRepository ratingRepository;
	
	@InjectMocks
	private RatingServiceImpl ratingService;
	
	
	@Test
	void testSaveRating() {
		Rating rating = new Rating(1, "Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		
		given(ratingRepository.save(rating)).willReturn(rating);
		
		Rating ratingSaved = ratingService.createRating(rating);
		
		assertThat(ratingSaved).isNotNull();
		assertEquals(rating, ratingSaved);
		verify(ratingRepository).save(any(Rating.class));
	}
	
	@Test
	void testUpdateRating() {
		Rating rating = new Rating(1, "Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		
		given(ratingRepository.save(rating)).willReturn(rating);
		
		Rating ratingUpdated = ratingService.updateRating(rating, 1);
		
		assertThat(ratingUpdated).isNotNull();
		assertEquals(rating, ratingUpdated);
		verify(ratingRepository).save(any(Rating.class));
	}
	
	@Test
	void testRatingList() {
		List<Rating> ratings = new ArrayList<>();
		ratings.add(new Rating(1, "Moodys Rating", "Sand PRating", "Fitch Rating", 10));
		ratings.add(new Rating(2, "Moodys Rating", "Sand PRating", "Fitch Rating", 10));
		ratings.add(new Rating(3, "Moodys Rating", "Sand PRating", "Fitch Rating", 10));
		
		
		given(ratingRepository.findAll()).willReturn(ratings);
		
		List<Rating> ratingList = ratingService.getRatingList();
		
		assertEquals(ratings, ratingList);
	}
	
	@Test
	void testGetById() {
		Integer id = 1;
		Rating rating = new Rating(1, "Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		
		given(ratingRepository.findById(rating.getId())).willReturn(Optional.of(rating));
		
		Rating ratingExcepted = ratingService.getById(id);
		
		assertThat(ratingExcepted).isNotNull();
		assertEquals(rating, ratingExcepted);
	}
	
	@Test
	void testDeleteRating() throws Exception {
		Integer id = 1;
		
		ratingService.deleteRating(id);
		ratingService.deleteRating(id);
		
		verify(ratingRepository, times(2)).deleteById(id);
	}

}
