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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RuleNameServiceTests {
	
	@Mock
	private RuleNameRepository ruleNameRepository;
	
	@InjectMocks
	RuleNameServiceImpl ruleNameService;
	
	@Test
	void testSaveRuleName() {
		RuleName ruleName = new RuleName(1, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		
		given(ruleNameRepository.save(ruleName)).willReturn(ruleName);
		
		RuleName ruleNameSaved = ruleNameService.createRuleName(ruleName);
		
		assertThat(ruleNameSaved).isNotNull();
		assertEquals(ruleName, ruleNameSaved);
		verify(ruleNameRepository).save(any(RuleName.class));
	}
	
	@Test
	void testUpdateRuleName() {
		RuleName ruleName = new RuleName(1, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		
		given(ruleNameRepository.save(ruleName)).willReturn(ruleName);
		
		RuleName ruleNameUpdated = ruleNameService.updateRuleName(ruleName, 1);
		
		assertThat(ruleNameUpdated).isNotNull();
		assertEquals(ruleName, ruleNameUpdated);
		verify(ruleNameRepository).save(any(RuleName.class));
	}
	
	@Test
	void testRuleNameList() {
		List<RuleName> ruleNames = new ArrayList<>();
		ruleNames.add(new RuleName(1, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part"));
		ruleNames.add(new RuleName(2, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part"));
		ruleNames.add(new RuleName(3, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part"));
		
		
		given(ruleNameRepository.findAll()).willReturn(ruleNames);
		
		List<RuleName> ruleName = ruleNameService.getRuleNameList();
		
		assertEquals(ruleNames, ruleName);
	}
	
	@Test
	void testGetById() {
		Integer id = 1;
		RuleName ruleName = new RuleName(1, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		
		given(ruleNameRepository.findById(ruleName.getId())).willReturn(Optional.of(ruleName));
		
		RuleName ruleNameExcepted = ruleNameService.getById(id);
		
		assertThat(ruleNameExcepted).isNotNull();
		assertEquals(ruleName, ruleNameExcepted);
	}
	
	@Test
	void testDeleteRuleName() throws Exception {
		Integer id = 1;
		
		ruleNameService.deleteRuleName(id);
		ruleNameService.deleteRuleName(id);
		
		verify(ruleNameRepository, times(2)).deleteById(id);
	}

}
