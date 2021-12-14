package com.robot.api.services.exceptions;

public class StandardError extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public StandardError(String error) {
		super("Erro ao executar ação: " + error);
	}

}
