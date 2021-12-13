package com.robot.api.entities.enums;

import com.robot.api.services.exceptions.StandardError;

public enum RobotView {
	
	N(0),
	E(90),
	S(180),
	W(270);
	
	private int code;

	private RobotView(int i) { 
		this.code = i;				
	}
	
	public int getCode() {
		return code;
	}
	
	public static RobotView valueOf(int i) {
		for (RobotView value : RobotView.values()) {
			if (value.getCode() == i) {
				return value;
			}
		}
		throw new StandardError("Visão do robô inválida!");
	}
}
