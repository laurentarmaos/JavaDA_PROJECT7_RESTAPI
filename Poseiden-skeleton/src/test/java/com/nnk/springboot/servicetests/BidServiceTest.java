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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BidServiceTest {
	
	@Mock
	private BidListRepository bidListRepository;
	
	@InjectMocks
	private BidListServiceImpl bidListService;
	
	@Test
	void testSavedBid() {
		BidList bid = new BidList(1, "Account Test", "Type Test", 10d);

		given(bidListRepository.save(bid)).willReturn(bid);
		
		BidList bidSaved = bidListService.createBidList(bid);

		
		assertThat(bidSaved).isNotNull();
		verify(bidListRepository).save(any(BidList.class));
	}

	@Test
	void testUpdateBid() {
		BidList bid = new BidList(1, "Account Test", "Type Test", 10d);
		
		given(bidListRepository.findById(bid.getBidListId())).willReturn(Optional.of(bid));
		given(bidListRepository.save(bid)).willReturn(bid);
		
		BidList bidUpdated = bidListService.updateBidList(bid, 1);
		
		assertThat(bidUpdated).isNotNull();
		verify(bidListRepository).save(any(BidList.class));
	}
	
	@Test
	void testBidList() {
		List<BidList> bids = new ArrayList<>();
		bids.add(new BidList(1, "Account Test", "Type Test", 10d));
		bids.add(new BidList(2, "Account Test", "Type Test", 10d));
		bids.add(new BidList(3, "Account Test", "Type Test", 10d));
		
		
		given(bidListRepository.findAll()).willReturn(bids);
		
		List<BidList> bidList = bidListService.getBidList();
		
		assertEquals(bids, bidList);
	}
	
	
	@Test
	void testGetById() {
		Integer id = 1;
		BidList bid = new BidList(1, "Account Test", "Type Test", 10d);
		
		given(bidListRepository.findById(bid.getBidListId())).willReturn(Optional.of(bid));
		
		BidList bidExcepted = bidListService.getById(id);
		
		assertThat(bidExcepted).isNotNull();
	}
	
	
	@Test
	void testDeleteBidList() throws Exception {
		Integer id = 1;
		
		bidListService.deleteBidList(id);
		bidListService.deleteBidList(id);
		
		verify(bidListRepository, times(2)).deleteById(id);
	}
}
