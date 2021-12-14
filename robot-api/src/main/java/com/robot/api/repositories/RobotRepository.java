package com.robot.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robot.api.entities.Robot;
import com.robot.api.entities.Terrain;

public interface RobotRepository extends JpaRepository<Robot, String>{
	
	public static Robot makeMove(Terrain terrain, String moves) {
		return new Robot(terrain,moves);
	};

}
