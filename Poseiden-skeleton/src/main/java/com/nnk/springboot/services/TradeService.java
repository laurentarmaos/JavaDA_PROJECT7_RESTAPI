package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Trade;

public interface TradeService {
	
	List<Trade> getTradeList();
	
	Trade getById(Integer id);
	
	Trade createTrade(Trade dto);
	
	Trade updateTrade(Trade dto, Integer id);
	
	void deleteTrade(Integer id) throws Exception;

}
