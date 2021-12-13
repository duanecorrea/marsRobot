package com.robot.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robot.api.entities.Robot;
import com.robot.api.services.RobotService;
import com.robot.api.services.exceptions.StandardError;

@RestController
@RequestMapping(value = "/rest/mars")
public class RobotResource {
	
	@Autowired
	private RobotService service;
	
	@PostMapping(value = "/")
	public ResponseEntity<String> setPosition() {				
		return ResponseEntity.badRequest().body("Nenhuma informação enviada para o robô.");				
	}
	
	@PostMapping(value = "/{moves}")
	public ResponseEntity<String> setPosition(@PathVariable String moves) {
		try {
			Robot ret = service.makeMove(moves);
			return ResponseEntity.ok().body(ret.toString());
		}catch(StandardError e) {			
			return ResponseEntity.badRequest().body(e.getMessage());			
		}		
	}

}
