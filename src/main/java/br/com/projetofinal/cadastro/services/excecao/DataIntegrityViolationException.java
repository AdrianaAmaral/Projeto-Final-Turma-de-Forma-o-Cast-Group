package br.com.projetofinal.cadastro.services.excecao;

public class DataIntegrityViolationException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public DataIntegrityViolationException(String message, Throwable cause) {
			super(message, cause);
		}

		public DataIntegrityViolationException(String message) {
			super(message);
		}

}
