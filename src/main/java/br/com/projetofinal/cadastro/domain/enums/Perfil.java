package br.com.projetofinal.cadastro.domain.enums;

public enum Perfil {

	ADMIN(0, "ADMINISTRADOR"), FUNCIONARIO(1, "FUNCIONARIO");

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
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescrição() {
		return descrição;
	}
	public void setDescrição(String descrição) {
		this.descrição = descrição;
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


