package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameService {
	
	List<RuleName> getRuleNameList();
	
	RuleName getById(Integer id);
	
	RuleName createRuleName(RuleName dto);
	
	RuleName updateRuleName(RuleName dto, Integer id);
	
	void deleteRuleName(Integer id) throws Exception;

}
