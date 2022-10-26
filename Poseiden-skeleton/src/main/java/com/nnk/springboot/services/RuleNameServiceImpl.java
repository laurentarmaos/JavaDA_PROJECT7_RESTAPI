package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameServiceImpl implements RuleNameService{
	
	@Autowired
	RuleNameRepository ruleNameRepository;
	
	@Override
	public List<RuleName> getRuleNameList() {
		
		return ruleNameRepository.findAll();
	}

	@Override
	public RuleName getById(Integer id) {

		RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		
		return ruleName;
	}

	@Override
	public void createRuleName(RuleName dto) {

		ruleNameRepository.save(dto);
		
	}

	@Override
	public void updateRuleName(RuleName dto, Integer id) {

		dto.setId(id);
		
		ruleNameRepository.save(dto);
		
	}

	@Override
	public void deleteRuleName(Integer id) throws Exception {

		ruleNameRepository.deleteById(id);
		
	}

}
