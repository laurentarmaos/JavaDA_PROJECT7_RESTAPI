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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTests {
	
	@Mock
	private CurvePointRepository curvePointRepository;
	
	@InjectMocks
	private CurvePointServiceImpl curvePointService;
	
	@Test
	void testSaveCurve() {
		CurvePoint curvePoint = new CurvePoint(1, 1, 30d);
		
		given(curvePointRepository.save(curvePoint)).willReturn(curvePoint);
		
		CurvePoint curvePointSaved = curvePointService.createCurvePoint(curvePoint);
		
		assertThat(curvePointSaved).isNotNull();
		assertEquals(curvePoint, curvePointSaved);
		verify(curvePointRepository).save(any(CurvePoint.class));
	}
	
	@Test
	void testUpdateCurve() {
		CurvePoint curvePoint = new CurvePoint(1, 1, 30d);
		
		given(curvePointRepository.findById(curvePoint.getId())).willReturn(Optional.of(curvePoint));
		given(curvePointRepository.save(curvePoint)).willReturn(curvePoint);
		
		CurvePoint curvePointUpdated = curvePointService.updateCurvePoint(curvePoint, 1);
		
		assertThat(curvePointUpdated).isNotNull();
		assertEquals(curvePoint, curvePointUpdated);
		verify(curvePointRepository).save(any(CurvePoint.class));
	}
	
	@Test
	void testCurveList() {
		List<CurvePoint> curves = new ArrayList<>();
		curves.add(new CurvePoint(1, 1, 30d));
		curves.add(new CurvePoint(2, 1, 30d));
		curves.add(new CurvePoint(3, 1, 30d));
		
		
		given(curvePointRepository.findAll()).willReturn(curves);
		
		List<CurvePoint> curveList = curvePointService.getCurveList();
		
		assertEquals(curves, curveList);
	}
	
	@Test
	void testGetById() {
		Integer id = 1;
		CurvePoint curve = new CurvePoint(1, 1, 30d);
		
		given(curvePointRepository.findById(curve.getId())).willReturn(Optional.of(curve));
		
		CurvePoint curveExcepted = curvePointService.getById(id);
		
		assertThat(curveExcepted).isNotNull();
		assertEquals(curve, curveExcepted);
	}
	
	@Test
	void testDeleteCurve() throws Exception {
		Integer id = 1;
		
		curvePointService.deleteCurvePoint(id);
		curvePointService.deleteCurvePoint(id);
		
		verify(curvePointRepository, times(2)).deleteById(id);
	}

}
