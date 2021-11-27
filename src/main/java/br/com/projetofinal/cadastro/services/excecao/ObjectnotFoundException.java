package br.com.projetofinal.cadastro.services.excecao;

public class ObjectnotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	//Generate Constructors from superclass
	public ObjectnotFoundException (String message, Throwable cause) { //throwable é a causa da exceção
		super(message, cause);
	}

	public ObjectnotFoundException (String message) {
		super(message);
	}

}
