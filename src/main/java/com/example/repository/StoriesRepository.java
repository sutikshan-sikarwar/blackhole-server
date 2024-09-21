package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Story;

public interface StoriesRepository extends JpaRepository<Story, Integer> {
	
	public List<Story> findByUserId(Integer userId);
}
