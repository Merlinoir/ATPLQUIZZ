package com.atplquiz.testUtils;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import com.atplquiz.entity.Qcm;

public class QcmUtils {
	
	public static Qcm getOneQcm(){
		return new Qcm("1", 1L, new DateTime("01012016").toDate());
		
	}

	public static List<Qcm> getQcmListSameUser() {
		List<Qcm> qcmList = new ArrayList<Qcm>();
		qcmList.add(new Qcm("1", 1L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("2", 1L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("3", 1L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("4", 1L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("5", 1L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("6", 1L, new DateTime("01012016").toDate()));
		return qcmList;
	}

	public static List<Qcm> getQcmListDifferentUser() {
		List<Qcm> qcmList = new ArrayList<Qcm>();
		qcmList.add(new Qcm("1", 1L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("2", 2L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("3", 3L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("4", 4L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("5", 5L, new DateTime("01012016").toDate()));
		qcmList.add(new Qcm("6", 6L, new DateTime("01012016").toDate()));
		return qcmList;
	}

}
