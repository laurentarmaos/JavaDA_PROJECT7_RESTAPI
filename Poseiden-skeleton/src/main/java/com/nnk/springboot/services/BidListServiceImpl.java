package com.nnk.springboot.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListServiceImpl implements BidListService {
	
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
	public void createBidList(BidList dto) {

		dto.setCreationDate(new Timestamp(System.currentTimeMillis()));
		dto.setCreationName(dto.getAccount());
		
		bidListRepository.save(dto);
		
	}

	@Override
	public void updateBidList(BidList dto, Integer id) {

		BidList bidList = bidListRepository.findById(id).get();
		

		bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
		bidList.setAccount(dto.getAccount());
		bidList.setType(dto.getType());
		bidList.setRevisionName(dto.getAccount());
		
		bidListRepository.save(bidList);
		
	}

	@Override
	public void deleteBidList(Integer id) throws Exception {

		bidListRepository.deleteById(id);

	}

	

}
