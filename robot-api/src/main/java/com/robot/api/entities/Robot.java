package com.robot.api.entities;

import com.robot.api.entities.enums.RobotView;
import com.robot.api.services.exceptions.StandardError;

public class Robot {
	
	private Integer positionX;
	private Integer positionY;
	private Integer view;
	private Terrain terrain;
	
	public Robot() {		
		
	}	
	
	public Robot(Integer positionX, Integer positionY, Integer view, Terrain terrain) {	
		this.positionX = positionX;
		this.positionY = positionY;
		this.terrain = terrain;
		this.view = view;
	}
	
	public Integer getPositionX() {
		return positionX;
	}
	public void setPositionX(Integer positionX) {
		this.positionX = positionX;
	}
	public Integer getPositionY() {
		return positionY;
	}
	public void setPositionY(Integer positionY) {
		this.positionY = positionY;
	}
	public Integer getView() {
		return view;
	}	
	
	public Terrain getTerrain() {
		return terrain;
	}

	public void setView(Integer view) {
		
		if (view <= 0 && this.view == 0)
			this.view = 360;
		
		this.view+=view;		
	}
	
	public void move() {
		switch (RobotView.valueOf(view)) {
			case N:
				positionY = positionY + 1;
				break;
			case E:
				positionX = positionX + 1;				
				break;
			case S:
				positionY = positionY - 1;			
				break;
			case W:
				positionX = positionX - 1;
				break;
			default:
				throw new StandardError("Visualização inválida!");
		}
		
		checkTerrainPosition();		
	}
	
	boolean checkTerrainPosition() {
		
		if (positionX < 0 || positionX > terrain.getWidth())
			throw new StandardError("Posição inválida.");
		
		if (positionY < 0 || positionY > terrain.getHeight())
			throw new StandardError("Posição inválida.");		
		
		return true;		
	}

	@Override
	public String toString() {
		return "(" + positionX + ", " + positionY + ", " + RobotView.valueOf(view) + ")";					
	}	

}
