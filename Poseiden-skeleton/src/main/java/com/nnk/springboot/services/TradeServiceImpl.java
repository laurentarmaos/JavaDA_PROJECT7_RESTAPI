package com.nnk.springboot.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	TradeRepository tradeRepository;
	
	@Override
	public List<Trade> getTradeList() {
		
		return tradeRepository.findAll();
	}

	@Override
	public Trade getById(Integer id) {

		Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		
		return trade;
	}

	@Override
	public void createTrade(Trade dto) {
		
		dto.setCreationDate(new Timestamp(System.currentTimeMillis()));
		dto.setCreationName(dto.getAccount());

		tradeRepository.save(dto);
		
	}


	@Override
	public void updateTrade(Trade dto, Integer id) {

		Trade trade = tradeRepository.findById(id).get();
		
		trade.setRevisionDate(new Timestamp(System.currentTimeMillis()));
		trade.setRevisionName(dto.getAccount());
		trade.setAccount(dto.getAccount());
		trade.setType(dto.getType());
		trade.setBuyQuantity(dto.getBuyQuantity());
		
		tradeRepository.save(trade);
		
	}

	@Override
	public void deleteTrade(Integer id) throws Exception {

		tradeRepository.deleteById(id);
		
	}

}
