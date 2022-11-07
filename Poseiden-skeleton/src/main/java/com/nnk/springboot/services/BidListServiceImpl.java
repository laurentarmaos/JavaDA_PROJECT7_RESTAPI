package com.nnk.springboot.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListServiceImpl implements BidListService {
	
	Logger log = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
	private BidListRepository bidListRepository;
	
	
	@Override
	public List<BidList> getBidList() {

		return bidListRepository.findAll();
		
	}
	
	@Override
	public BidList getById(Integer id) {
		
		BidList bidList =  bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		
		return bidList;
	}

	@Override
	public BidList createBidList(BidList dto) {

		dto.setCreationDate(new Timestamp(System.currentTimeMillis()));
		dto.setCreationName(dto.getAccount());
		
		log.info("Service : bid " + dto.getCreationName() + " created");
		return bidListRepository.save(dto);
		
	}

	@Override
	public BidList updateBidList(BidList dto, Integer id) {

		BidList bidList = bidListRepository.findById(id).get();
		

		bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
		bidList.setAccount(dto.getAccount());
		bidList.setType(dto.getType());
		bidList.setRevisionName(dto.getAccount());
		
		log.info("Service : bid with id " + id + " updated");
		return bidListRepository.save(bidList);
		
	}

	@Override
	public void deleteBidList(Integer id) throws Exception {

		log.info("Service : bid with id " + id + " deleted");
		bidListRepository.deleteById(id);

	}

	

}
