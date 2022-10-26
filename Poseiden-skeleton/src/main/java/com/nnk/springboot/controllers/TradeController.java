package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

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
public class TradeController {

	Logger log = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
	TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("trades", tradeService.getTradeList());
        log.info("get list of all trades");
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
    	model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") Trade trade, BindingResult result, Model model) {

    	if(result.hasErrors()) {
    		log.error("error : " + result);
    		return "trade/add";
    	}
    	
    	tradeService.createTrade(trade);
    	model.addAttribute("trades", tradeService.getTradeList());
    	log.info("new curvePoint created : " + trade.getTradeId() + ", redirect to /trade/list");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    	try {
			Trade trade = tradeService.getById(id);
			model.addAttribute("trade", trade);
    		log.info("get trade by id : " + id);
    		return "trade/update";
		} catch (Exception e) {
			model.addAttribute("errorId", "Invalid trade Id:" + id);
			log.error("error, invalid trade id : " + id);
			return "trade/update";
		}

    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {

    	if(result.hasErrors()) {
    		log.error("error : " + result);
    		return "trade/update";
    	}
    	
    	tradeService.updateTrade(trade, id);
    	log.info("updated curvePoint : " + trade.getTradeId());
    	model.addAttribute("trades", tradeService.getTradeList());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        
    	try {
			tradeService.deleteTrade(id);
			log.info("deleted trade with id : " + id);
		} catch (Exception e) {
			log.error("invalid id : " + id);
		}
    	
    	model.addAttribute("trades", tradeService.getTradeList());
        return "redirect:/trade/list";
    }
}
