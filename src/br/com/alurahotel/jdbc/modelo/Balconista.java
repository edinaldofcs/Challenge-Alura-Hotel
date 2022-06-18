package br.com.alurahotel.jdbc.modelo;

public class Balconista extends Funcionario implements Autenticavel {

	/*@Override
	public double getBonificacao() {
		return 10;
	}*/

	// Autenticavel
	private AutenticarSenha autenticador;

	public Balconista() {
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

}
