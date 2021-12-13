package com.robot.api.services.exceptions;

public class StandardError extends RuntimeException {
	
	public StandardError(String error) {
		super("Erro ao executar ação: " + error);
	}

}
