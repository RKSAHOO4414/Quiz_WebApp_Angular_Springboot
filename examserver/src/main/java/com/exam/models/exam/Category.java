package com.exam.models.exam;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "exam_category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cid;
	
	private String title;
	
	private String description;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Quiz> quizzes = new LinkedHashSet<>();

	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(String title, String description, Set<Quiz> quizzes) {
		this.title = title;
		this.description = description;
		this.quizzes = quizzes;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(Set<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", title=" + title + ", description=" + description + ", quizzes=" + quizzes
				+ "]";
	}
	
	
}
