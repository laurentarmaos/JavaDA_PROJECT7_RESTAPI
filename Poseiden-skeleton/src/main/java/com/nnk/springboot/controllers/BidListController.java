package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

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
public class BidListController {
	
	Logger log = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
	private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
    	model.addAttribute("bids", bidListService.getBidList());
    	log.info("get list of all bids");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
    	model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bidList, BindingResult result, Model model) {
    	
    	if(result.hasErrors()) {
    		log.error("error : " + result);
    		return "bidList/add";
    	}
    	
    	bidListService.createBidList(bidList);
    	model.addAttribute("bids", bidListService.getBidList());
    	log.info("new bid created : " + bidList.getAccount() + ", redirect to /bidList/list");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    	try {
    		BidList bidList = bidListService.getById(id);
    		model.addAttribute("bidList", bidList);
    		log.info("get bid by id : " + id);
            return "bidList/update";
		} catch (Exception e) {
			model.addAttribute("errorId", "Invalid bid Id:" + id);
			log.error("error, invalid bidList id : " + id);
            return "bidList/update";
		}
    	
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	
    	if(result.hasErrors()) {
    		log.error("error : " + result);
    		return "bidList/update";
    	}
    	
    	bidListService.updateBidList(bidList, id);
    	log.info("updated bid : " + bidList.getCreationName());
    	model.addAttribute("bids", bidListService.getBidList());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
 
    	try {
			bidListService.deleteBidList(id);
			log.info("deleted bid with id : " + id);
		} catch (Exception e) {
			log.error("invalid id : " + id);
		}
    	
    	model.addAttribute("bids", bidListService.getBidList());
        return "redirect:/bidList/list";
    }
}
