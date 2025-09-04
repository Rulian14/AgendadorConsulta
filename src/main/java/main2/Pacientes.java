package main2;

public class Pacientes extends Pessoas {
	private StringBuilder historico = new StringBuilder();
	
	public Pacientes(String nome, String cpf, String email, String telefone) {
		super(nome, cpf, email, telefone);

	}
	public void adicionarHistorico(String anotacao) {
		historico.append(anotacao).append("\n");
	}
	public String getHistorico() {
		return historico.toString();
	}
	public void setHistorico(StringBuilder historico) {
		this.historico = historico;
	}
	

}
