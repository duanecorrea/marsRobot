package com.robot.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.robot.api.entities.Robot;
import com.robot.api.entities.Terrain;
import com.robot.api.repositories.RobotRepository;
import com.robot.api.services.exceptions.StandardError;

@SpringBootTest
class RobotApiApplicationTests {
	
	// Estes testes são específicos para este projeto, considerando um terreno de 5x5
		
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
	
	// Novos cenários de teste
	
	
	@Test
	public void validDiagonalMovement() {
		
		//Faz um caminho diagonal no terreno
		
		Robot robot = RobotRepository.makeMove(terrain, "RMLMRMLMRMLMRMLM");	
		assertThat(robot.getPositionX()).isEqualTo(4);
		assertThat(robot.getPositionY()).isEqualTo(4);
		assertThat(robot.getCoordinate()).isEqualTo("N");
	}	
	
	@Test
	public void validRoundTerrainCheck() {
		
		//Se move entre os limites do terreno e volta pra base
		
		Robot robot = RobotRepository.makeMove(terrain, "MMMMRMMMMRMMMMRMMMMR");	
		assertThat(robot.getPositionX()).isEqualTo(0);
		assertThat(robot.getPositionY()).isEqualTo(0);
		assertThat(robot.getCoordinate()).isEqualTo("N");
	}	
	
	@Test
	public void validAllTerrainCheck() {	
		
		//Robô faz a ronda ao redor da base, anda até o canto superior esquerdo e realiza a ronda
		//Se dirige para o canto superior direito e faz a ronda
		//Depois até o canto inferior direito para fazer a ronda
		//Sua última ronda é feita no centro do terreno
		//Por fim volta para a posição inicial na base	
		
		Robot robot = RobotRepository.makeMove(terrain, "RRRRMMMMLLLMMMMLLLMMMMLLLMMRMMRRRMMLMMLL");	
		assertThat(robot.getPositionX()).isEqualTo(0);
		assertThat(robot.getPositionY()).isEqualTo(0);
		assertThat(robot.getCoordinate()).isEqualTo("N");
	}
	
	@Test
	public void validEmpty() {
		
		// Valida se foi enviado pelo menos um comando para o robô
		
		Robot robot = new Robot();
		try {
			robot = RobotRepository.makeMove(terrain, "");
		} catch (Exception e) {
			assertThat(e.getClass()).isEqualTo(StandardError.class);
		}			
		
		checkHasToBeError(robot);
	}
	
	@Test
	public void validOutOfBound() {
		
		//Mais uma posição inválida
		
		Robot robot = new Robot();
		try {
			robot = RobotRepository.makeMove(terrain, "MMLM");
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
