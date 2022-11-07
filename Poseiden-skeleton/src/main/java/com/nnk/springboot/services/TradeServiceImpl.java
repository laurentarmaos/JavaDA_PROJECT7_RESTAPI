package com.nnk.springboot.services;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeServiceImpl implements TradeService {
	
	Logger log = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
	TradeRepository tradeRepository;
	
	@Override
	public List<Trade> getTradeList() {
		
		return tradeRepository.findAll();
	}

	@Override
	public Trade getById(Integer id) {

		Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		
		return trade;
	}

	@Override
	public Trade createTrade(Trade dto) {
		
		dto.setCreationDate(new Timestamp(System.currentTimeMillis()));
		dto.setCreationName(dto.getAccount());

		log.info("Service : trade with id " + dto.getCreationName() + " created");
		return tradeRepository.save(dto);
		
	}


	@Override
	public Trade updateTrade(Trade dto, Integer id) {

		Trade trade = tradeRepository.findById(id).get();
		
		trade.setRevisionDate(new Timestamp(System.currentTimeMillis()));
		trade.setRevisionName(dto.getAccount());
		trade.setAccount(dto.getAccount());
		trade.setType(dto.getType());
		trade.setBuyQuantity(dto.getBuyQuantity());
		
		log.info("Service : trade with id " + id + " updated");
		return tradeRepository.save(trade);
		
	}

	@Override
	public void deleteTrade(Integer id) throws Exception {

		log.info("Service : trade with id " + id + " deleted");
		tradeRepository.deleteById(id);
		
	}

}
