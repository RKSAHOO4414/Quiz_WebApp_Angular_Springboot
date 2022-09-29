package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//add question
	@PostMapping("/")
	public ResponseEntity<Question> add(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	//update the question
	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
	
	//get all question of any qid
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid){
		/*Quiz quiz = new Quiz();
		quiz.setqId(qid);
		Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);*/
		
		Quiz quiz = this.quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		List list = new ArrayList<>(questions);
		
		if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
				list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("qid") Long qid){
		Quiz quiz = new Quiz();
		quiz.setqId(qid);
		Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);
		
		//return ResponseEntity.ok(list);
	}
	
	//get single question
	@GetMapping("/{quesId}")
	public Question get(@PathVariable("quesId") Long quesId) {
		return this.questionService.getQuestion(quesId);
	}
	
	//delete question
	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId) {
		this.questionService.deleteQuestion(quesId);
	}
	
	//evaluate quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){

		System.out.println(questions);
		
		double marksGot = 0;
		int correctAnswers = 0;
		int attempted = 0;
		
		for(Question q : questions){
			//single questios
			
			Question question = this.questionService.get(q.getQuesId());
			if(question.getAnswer().trim().equals(q.getGivenAns())) {
				//correct
				correctAnswers++;
				
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksGot += marksSingle;
				
			}
			
			if(q.getGivenAns() != null || q.getGivenAns().trim().equals("")) {
				attempted++;
			}
			
		}
		
		//Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
		Map<String, Object> map = new HashMap<>();
		map.put("marksGot", marksGot);
		map.put("correctAnswers", correctAnswers);
		map.put("attempted", attempted);
		
		return ResponseEntity.ok(map);
	}
	
}


