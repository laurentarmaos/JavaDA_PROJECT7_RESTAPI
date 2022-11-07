package com.nnk.springboot.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameServiceImpl implements RuleNameService{
	
	Logger log = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
	RuleNameRepository ruleNameRepository;
	
	@Override
	public List<RuleName> getRuleNameList() {
		
		return ruleNameRepository.findAll();
	}

	@Override
	public RuleName getById(Integer id) {

		RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
		
		return ruleName;
	}

	@Override
	public RuleName createRuleName(RuleName dto) {

		log.info("Service : ruleName with id " + dto.getName() + " created");
		return ruleNameRepository.save(dto);
		
	}

	@Override
	public RuleName updateRuleName(RuleName dto, Integer id) {

		dto.setId(id);
		
		log.info("Service : ruleName with id " + id + " updated");
		return ruleNameRepository.save(dto);
		
	}

	@Override
	public void deleteRuleName(Integer id) throws Exception {

		log.info("Service : ruleName with id " + id + " deleted");
		ruleNameRepository.deleteById(id);
		
	}

}
