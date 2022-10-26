package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {
	
	Logger log = LoggerFactory.getLogger(BidListController.class);

	@Autowired
	RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingService.getRatingList());
        log.info("get list of all ratings");
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
    	model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {

    	if(result.hasErrors()) {
    		log.error("error : " + result);
    		return "rating/add";
    	}
    	
    	ratingService.createRating(rating);
    	model.addAttribute("ratings", ratingService.getRatingList());
    	log.info("new rating created : " + rating.getId() + ", redirect to /rating/list");
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		try {
			Rating rating = ratingService.getById(id);
			model.addAttribute("rating", rating);
    		log.info("get rating by id : " + id);
    		return "rating/update";
		} catch (Exception e) {
			model.addAttribute("errorId", "Invalid rating Id:" + id);
			log.error("error, invalid rating id : " + id);
			return "rating/update";
		}
 
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
        	log.error("error : " + result);
    		return "rating/update";
        }
        
        ratingService.updateRating(rating, id);
        log.info("updated rating : " + rating.getId());
    	model.addAttribute("ratings", ratingService.getRatingList());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

    	try {
			ratingService.deleteRating(id);
			log.info("deleted rating with id : " + id);
		} catch (Exception e) {
			log.error("invalid id : " + id);
		}
    	
    	model.addAttribute("ratings", ratingService.getRatingList());
        return "redirect:/rating/list";
    }
}
