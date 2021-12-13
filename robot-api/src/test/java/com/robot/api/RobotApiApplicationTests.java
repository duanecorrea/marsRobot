package com.robot.api;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RobotApiApplicationTests {
	
	final String uri = "curl -s --request POST http://localhost:8080/rest/mars/";

	@Test
	public void validMovement1() {		
		 		
		try {
			Process process = Runtime.getRuntime().exec(uri + "MMRMMRMM");
			System.out.println(process);
			
			process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
