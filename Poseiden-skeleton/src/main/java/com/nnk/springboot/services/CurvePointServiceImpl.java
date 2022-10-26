package com.nnk.springboot.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointServiceImpl implements CurvePointService {
	
	@Autowired
	CurvePointRepository curvePointRepository;
	
	@Override
	public List<CurvePoint> getCurveList() {

		return curvePointRepository.findAll();
	}
	
	@Override
	public CurvePoint getById(Integer id) {

		CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));

		return curvePoint;
	}


	@Override
	public void createCurvePoint(CurvePoint dto) {

		dto.setCreationDate(new Timestamp(System.currentTimeMillis()));
		
		curvePointRepository.save(dto);
		
	}

	@Override
	public void updateCurvePoint(CurvePoint dto, Integer id) {

		CurvePoint curvePoint = curvePointRepository.findById(id).get();
		
		curvePoint.setAsOfDate(new Timestamp(System.currentTimeMillis()));
		curvePoint.setTerm(dto.getTerm());
		curvePoint.setValue(dto.getValue());
		
		curvePointRepository.save(curvePoint);
		
	}

	@Override
	public void deleteCurvePoint(Integer id) throws Exception {
		
		curvePointRepository.deleteById(id);
		
	}


}
