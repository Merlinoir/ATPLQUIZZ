package com.atplquiz.service;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.atplquiz.entity.Qcm;
import com.atplquiz.testUtils.QcmUtils;


@RunWith(MockitoJUnitRunner.class)
public class QcmServiceTest {

	@Mock
	JdbcTemplate  mockJdbcTemplate;

	@InjectMocks
	QcmService qcmService;


	@Test
	public void testFindAll(){		
		
		//GIVEN
		List<Qcm> qcmList = QcmUtils.getQcmListSameUser();
		RowMapper<Qcm> rowMapper = Mockito.any(RowMapper.class);
		Mockito.when(mockJdbcTemplate.query(Mockito.anyString(), rowMapper)).thenReturn(qcmList);
		
		//WHEN
		List<Qcm> retrievedList = qcmService.findAll();
		
		//THEN
		Assert.assertEquals("La liste doit contenir 6 Qcm", 6, retrievedList.size());
	}
}
