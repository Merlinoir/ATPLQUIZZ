package com.atplquiz.testUtils;

import java.util.ArrayList;
import java.util.List;

import com.atplquiz.entity.Question;

public class QuestionUtils {
	
	
	public static Question getOneQuestion(){
		return new Question(1L, "libelleQuestion1", 1L);	
	}
	
	public static List<Question> getQuestionListOneTheme(){
		List<Question> questionList = new ArrayList<Question>();
		questionList.add(getOneQuestion());
		questionList.add(new Question(2L, "libelleQuestion2", 1L));
		return questionList;
	}

}
