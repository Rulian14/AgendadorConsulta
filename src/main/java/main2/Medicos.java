package main2;

public class Medicos extends Pessoas {

	String especialidade;
	String crm;
	
	public Medicos(String nome, String cpf, String email, String telefone) {
		super(nome, cpf, email, telefone);
	}
	
	public void fazerProntuario(Pacientes paciente, String anotacao) {
	    paciente.adicionarHistorico("Dr." + this.getNome() + ": " + anotacao);
	}
}
