package com.github.civcraft.zeus.model.yaml;

public class InvalidYamlFormatException extends RuntimeException {

	private static final long serialVersionUID = 3339861614148391629L;

	public InvalidYamlFormatException() {
		super();
	}

	public InvalidYamlFormatException(String msg) {
		super(msg);
	}

}
