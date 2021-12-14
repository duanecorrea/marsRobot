package com.robot.api.entities;

import com.robot.api.entities.enums.RobotView;
import com.robot.api.services.exceptions.StandardError;

public class Robot {
	
	private Integer positionX;
	private Integer positionY;
	private Integer view;
	private String movements;
	private Terrain terrain;
	
	public Robot() {
		this.positionX = 0;
		this.positionY = 0;
		this.view      = 0;		
	}	
	
	public Robot(Terrain terrain, String movements) {	
		this.positionX = 0;
		this.positionY = 0;
		this.view      = 0;		
		this.terrain   = terrain;
		this.movements = movements;		
		setMovements(movements);
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
	
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public Terrain getTerrain() {
		return terrain;
	}	

	public String getMovements() {
		return movements;
	}

	public void setView(Integer view) {
		
		if (view <= 0 && this.view == 0)
			this.view = 360;
		
		this.view+=view;		
	}
	
	public String getCoordinate() {
		
		return RobotView.valueOf(view).toString();
		
	}
	
	public void setMovements(String moves) {
		
		if(moves == "")
			throw new StandardError("Nenhuma informação enviada para o robô.");
		
		for (char ch : moves.toCharArray()) {			  
			switch (ch) {
			case 'L':
				setView(-90);				
				break;
			case 'R':
				setView(90);				
				break;
			case 'M':
				move();				
				break;
			default:
				throw new StandardError("Comando inválido.");
			}
		}
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
	
	public boolean checkTerrainPosition() {
		
		if (positionX < 0 || positionX > terrain.getWidth())
			throw new StandardError("Posição inválida.");
		
		if (positionY < 0 || positionY > terrain.getHeight())
			throw new StandardError("Posição inválida.");		
		
		return true;		
	}

	@Override
	public String toString() {
		return "(" + positionX + ", " + positionY + ", " + getCoordinate() + ")";					
	}	

}
