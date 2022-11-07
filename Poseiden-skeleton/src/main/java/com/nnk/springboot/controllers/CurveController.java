package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

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
public class CurveController {
	
	Logger log = LoggerFactory.getLogger(BidListController.class);

	@Autowired
	CurvePointService curvePointService;


    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        model.addAttribute("curvePoints", curvePointService.getCurveList());
        log.info("Controller : get list of all curvePoints");
        return "curvePoint/list";
    }


    @GetMapping("/curvePoint/add")
    public String addCurveForm(Model model) {
    	model.addAttribute("curvePoint", new CurvePoint());
        return "curvePoint/add";
    }

    
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePoint curvePoint, BindingResult result, Model model) {

    	if(result.hasErrors()) {
    		log.error("Controller : error : " + result);
    		return "curvePoint/add";
    	}
    	
    	curvePointService.createCurvePoint(curvePoint);
    	model.addAttribute("curvePoints", curvePointService.getCurveList());
    	log.info("Controller : new curvePoint created : " + curvePoint.getId() + ", redirect to /curvePoint/list");
    	return "redirect:/curvePoint/list";
    }


    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    	try {
    		CurvePoint curvePoint = curvePointService.getById(id);
    		model.addAttribute("curvePoint", curvePoint);
    		log.info("Controller : get curvePoint by id : " + id);
    		return "curvePoint/update";
		} catch (Exception e) {
			model.addAttribute("errorId", "Invalid curve Id:" + id);
			log.error("Controller : error, invalid curvePoint id : " + id);
			return "curvePoint/update";
		}	
    }


    @PostMapping("/curvePoint/update/{id}")
    public String updateCurve(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {

    	if(result.hasErrors()) {
    		log.error("Controller : error : " + result);
    		return "curvePoint/update";
    	}
    	
    	curvePointService.updateCurvePoint(curvePoint, id);
    	log.info("Controller : updated curvePoint : " + curvePoint.getId());
    	model.addAttribute("curvePoints", curvePointService.getCurveList());
        return "redirect:/curvePoint/list";
    }


    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurve(@PathVariable("id") Integer id, Model model) {

    	try {
    		curvePointService.deleteCurvePoint(id);
    		log.info("Controller : deleted curvePoint with id : " + id);
		} catch (Exception e) {
			log.error("Controller : invalid id : " + id);
		}
    	
    	model.addAttribute("curvePoints", curvePointService.getCurveList());
        return "redirect:/curvePoint/list";
    }
}
