package com.robot.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.robot.api.entities.Robot;
import com.robot.api.entities.Terrain;
import com.robot.api.repositories.RobotRepository;
import com.robot.api.services.exceptions.StandardError;

@SpringBootTest
class RobotApiApplicationTests {
	
	private RobotRepository repository;
	private Terrain terrain = new Terrain(5,5);
		

	@Test
	public void validMovement1() {
		
		Robot robot = new Robot();				
		try {
			robot = RobotRepository.makeMove(terrain, "MMRMMRMM");
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		assertThat(robot.getPositionX()).isEqualTo(2);
		assertThat(robot.getPositionY()).isEqualTo(0);
		assertThat(robot.getCoordinate()).isEqualTo("S");
	}
	
	@Test
	public void validMovement2() {
		
		Robot robot = RobotRepository.makeMove(terrain, "MML");	
		assertThat(robot.getPositionX()).isEqualTo(0);
		assertThat(robot.getPositionY()).isEqualTo(2);
		assertThat(robot.getCoordinate()).isEqualTo("W");
	}
	
	@Test
	public void validInvalidCommand() {
					
		Robot robot = new Robot();
		try {
			robot = RobotRepository.makeMove(terrain, "AAA");
		} catch (Exception e) {
			assertThat(e.getClass()).isEqualTo(StandardError.class);
		}	
		
		checkHasToBeError(robot);
	}
	
	@Test
	public void validInvalidPosition() {
		
		Robot robot = new Robot();
		try {
			robot = RobotRepository.makeMove(terrain, "MMMMMMMMMMMMMMMMMMMMMMMM");
		} catch (Exception e) {
			assertThat(e.getClass()).isEqualTo(StandardError.class);
		}	
		
		checkHasToBeError(robot);
	}
	
	@Test
	public void validEmpty() {
		
		Robot robot = new Robot();
		try {
			robot = RobotRepository.makeMove(terrain, "");
		} catch (Exception e) {
			assertThat(e.getClass()).isEqualTo(StandardError.class);
		}			
		
		checkHasToBeError(robot);
	}
	
	private void checkHasToBeError(Robot robot) {
		assertThat(robot.getPositionX()).isEqualTo(0);
		assertThat(robot.getPositionY()).isEqualTo(0);
		assertThat(robot.getCoordinate()).isEqualTo("N");		
	}
	

}
