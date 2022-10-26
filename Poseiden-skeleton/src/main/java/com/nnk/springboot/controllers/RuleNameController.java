package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

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
public class RuleNameController {
	
	Logger log = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
	RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("ruleNames", ruleNameService.getRuleNameList());
        log.info("get list of all ruleNames");
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
    	model.addAttribute("ruleName", new RuleName());
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result, Model model) {

    	if(result.hasErrors()) {
    		log.error("error : " + result);
    		return "ruleName/add";
    	}
    	
    	ruleNameService.createRuleName(ruleName);
    	model.addAttribute("ruleNames", ruleNameService.getRuleNameList());
    	log.info("new ruleName created : " + ruleName.getId() + ", redirect to /ruleName/list");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    	try {
    		RuleName ruleName = ruleNameService.getById(id);
    		model.addAttribute("ruleName", ruleName);
    		log.info("get ruleName by id : " + id);
    		return "ruleName/update";
		} catch (Exception e) {
			model.addAttribute("errorId", "Invalid ruleName Id:" + id);
			log.error("error, invalid ruleName id : " + id);
			return "ruleName/update";
		}
 
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {

    	if(result.hasErrors()) {
    		log.error("error : " + result);
    		return "ruleName/update";
    	}
    	
    	ruleNameService.updateRuleName(ruleName, id);
    	log.info("updated ruleName : " + ruleName.getId());
    	model.addAttribute("ruleNames", ruleNameService.getRuleNameList()); 
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

    	try {
    		ruleNameService.deleteRuleName(id);
    		log.info("deleted ruleName with id : " + id);
		} catch (Exception e) {
			log.error("invalid id : " + id);
		}
    	
    	model.addAttribute("ruleNames", ruleNameService.getRuleNameList()); 
        return "redirect:/ruleName/list";
    }
}
