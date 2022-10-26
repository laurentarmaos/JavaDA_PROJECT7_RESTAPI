package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.BidList;

public interface BidListService {
	
	List<BidList> getBidList();
	
	BidList getById(Integer id);

	void createBidList(BidList dto);
	
	void updateBidList(BidList dto, Integer id);
	
	void deleteBidList(Integer id) throws Exception;
}
