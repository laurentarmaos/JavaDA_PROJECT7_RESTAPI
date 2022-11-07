package com.nnk.springboot.services;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointServiceImpl implements CurvePointService {
	
	Logger log = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
	CurvePointRepository curvePointRepository;
	
	@Override
	public List<CurvePoint> getCurveList() {

		return curvePointRepository.findAll();
	}
	
	@Override
	public CurvePoint getById(Integer id) {

		CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));

		return curvePoint;
	}


	@Override
	public CurvePoint createCurvePoint(CurvePoint dto) {

		dto.setCreationDate(new Timestamp(System.currentTimeMillis()));
		
		log.info("Service : curve created");
		return curvePointRepository.save(dto);
		
	}

	@Override
	public CurvePoint updateCurvePoint(CurvePoint dto, Integer id) {

		CurvePoint curvePoint = curvePointRepository.findById(id).get();
		
		curvePoint.setAsOfDate(new Timestamp(System.currentTimeMillis()));
		curvePoint.setTerm(dto.getTerm());
		curvePoint.setValue(dto.getValue());
		
		log.info("Service : curve " + id + " updated");
		return curvePointRepository.save(curvePoint);
		
	}

	@Override
	public void deleteCurvePoint(Integer id) throws Exception {
		
		log.info("Service : curve " + id + " updated");
		curvePointRepository.deleteById(id);
		
	}


}
