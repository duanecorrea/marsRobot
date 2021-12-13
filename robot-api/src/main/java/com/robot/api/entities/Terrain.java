package com.robot.api.entities;

public class Terrain {
	
	private Integer width;
	private Integer height;
	
	public Terrain() {
		
	}
	
	public Terrain(Integer width, Integer height) {		
		this.width = width -1;
		this.height = height -1;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width -1;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height -1;
	}	

}
