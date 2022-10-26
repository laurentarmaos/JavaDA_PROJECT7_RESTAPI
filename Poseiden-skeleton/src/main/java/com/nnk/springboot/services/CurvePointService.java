package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {
	
	List<CurvePoint> getCurveList();
	
	CurvePoint getById(Integer id);

	void createCurvePoint(CurvePoint dto);
	
	void updateCurvePoint(CurvePoint dto, Integer id);
	
	void deleteCurvePoint(Integer id) throws Exception;
}
