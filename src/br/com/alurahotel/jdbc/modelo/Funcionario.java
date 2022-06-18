package br.com.alurahotel.jdbc.modelo;

public abstract class Funcionario {	
	
	protected String nome;
	protected String cpf;
	protected String senha;
	private int id;
	
	//metodo sem implementacao
		//public abstract double getBonificacao();	
	
	
	public String getNome() {
		return this.nome;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
		

}
