package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Trade;

public interface TradeService {
	
	List<Trade> getTradeList();
	
	Trade getById(Integer id);
	
	void createTrade(Trade dto);
	
	void updateTrade(Trade dto, Integer id);
	
	void deleteTrade(Integer id) throws Exception;

}
