package com.robot.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robot.api.entities.Robot;
import com.robot.api.entities.Terrain;
import com.robot.api.repositories.RobotRepository;

@Service
public class RobotService {
		
	RobotRepository repository;
	
	public Robot makeMove(String moves) {		
		Terrain terrain = new Terrain(5,5);
		return RobotRepository.makeMove(terrain,moves);	
	}

}
