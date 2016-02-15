package com.atplquiz.testUtils;

import java.util.ArrayList;
import java.util.List;

import com.atplquiz.entity.Reponse;

public class ReponseUtils {
	
	
	public static Reponse getOneReponse(long idQuestion){
		return new Reponse(1L,"libelleReponse1", false, idQuestion);
	}
	public static List<Reponse>getReponseListTrueFirst(long idQuestion){
		List<Reponse>reponseList = new ArrayList<Reponse>();
		reponseList.add(new Reponse(1L, "libelleReponse1", true, idQuestion));
		reponseList.add(new Reponse(2L, "libelleReponse2", false, idQuestion));
		reponseList.add(new Reponse(3L, "libelleReponse3", false, idQuestion));
		reponseList.add(new Reponse(4L, "libelleReponse4", false, idQuestion));
		return reponseList;
	}
	
	public List<Reponse>getReponseListTrueSecond(long idQuestion){
		List<Reponse>reponseList = new ArrayList<Reponse>();
		reponseList.add(new Reponse(1L, "libelleReponse1", false, idQuestion));
		reponseList.add(new Reponse(2L, "libelleReponse2", true, idQuestion));
		reponseList.add(new Reponse(3L, "libelleReponse3", false, idQuestion));
		reponseList.add(new Reponse(4L, "libelleReponse4", false, idQuestion));
		return reponseList;
	}
	
	public List<Reponse>getReponseListTrueThird(long idQuestion){
		List<Reponse>reponseList = new ArrayList<Reponse>();
		reponseList.add(new Reponse(1L, "libelleReponse1", false, idQuestion));
		reponseList.add(new Reponse(2L, "libelleReponse2", false, idQuestion));
		reponseList.add(new Reponse(3L, "libelleReponse3", true, idQuestion));
		reponseList.add(new Reponse(4L, "libelleReponse4", false, idQuestion));
		return reponseList;
	}
	
	public List<Reponse>getReponseListTrueLast(long idQuestion){
		List<Reponse>reponseList = new ArrayList<Reponse>();
		reponseList.add(new Reponse(1L, "libelleReponse1", false, idQuestion));
		reponseList.add(new Reponse(2L, "libelleReponse2", false, idQuestion));
		reponseList.add(new Reponse(3L, "libelleReponse3", false, idQuestion));
		reponseList.add(new Reponse(4L, "libelleReponse4", true, idQuestion));
		return reponseList;
	}
}
