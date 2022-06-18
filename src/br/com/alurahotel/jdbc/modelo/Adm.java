package br.com.alurahotel.jdbc.modelo;

public class Adm extends Funcionario implements Autenticavel {

	/*@Override
	public double getBonificacao() {
		return 10;
	}*/

	// Autenticavel
	private AutenticarSenha autenticador;

	public Adm(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
		this.autenticador = new AutenticarSenha();
	}

	@Override
	public void setSenha(int senha) {
		this.autenticador.setSenha(senha);
	}

	@Override
	public boolean autenticar(int senha) {
		return this.autenticador.autenticar(senha);
	}
	
	@Override
	public int getId() {
		return super.getId();
	}

}
