package com.robot.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.robot.api.entities.Robot;
import com.robot.api.entities.Terrain;
import com.robot.api.services.exceptions.StandardError;

@Service
public class RobotService {
	
	public Robot makeMove(String moves) {
		Terrain terrain = new Terrain(5,5);
		Robot robot = new Robot(0,0,0,terrain);		
		
		List<Character> charList = new ArrayList<>();
		
		for (char ch : moves.toCharArray()) {			  
			charList.add(ch);
        }
		
		for (char ch : charList) {
			
			switch (ch) {
				case 'L':
					robot.setView(-90);				
					break;
				case 'R':
					robot.setView(90);				
					break;
				case 'M':
					robot.move();				
					break;
				default:
					throw new StandardError("Comando inválido.");
			}
		}
		
		return robot;		
	}

}
