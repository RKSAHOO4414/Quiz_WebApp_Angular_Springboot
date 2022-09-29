package com.exam.service;

import java.util.Set;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question); 
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestion();
	
	public Question getQuestion(Long questionId);
	
	public Set<Question> getQuestionOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Long quesId);
	
	public Question get(Long questionsId);
	
}
