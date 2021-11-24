package br.com.projetofinal.cadastro.domain.enums;

public enum Perfil {

	ADMIN(0, "ADMIN"), CLIENTE(1, "CLIENTE"), FUNCIONARIO(2, "FUNCIONARIO");

	// ATRIBUTOS
	private Integer codigo;
	private String descrição;
	
	//CONSTRUTOR
	private Perfil(Integer codigo, String descrição) {
		this.codigo = codigo;
		this.descrição = descrição;
	}
	
	//METODOS GET E SET
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescrição() {
		return descrição;
	}
		
	//METODOS
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
	}
		
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Perfil Inválido");
	}
}


