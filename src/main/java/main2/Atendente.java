package main2;

public class Atendente extends Pessoas{

	String ID;
	
	public Atendente(String nome, String cpf, String email, String telefone, String ID) {
		super(nome, cpf, email, telefone);
		this.ID = ID;
		
	}
	
	public void realizarAgendamento(Pacientes paciente) {
	    System.out.println("Agendamento realizado para: " + paciente.getNome());
	}
	public void exportarExcel() {
		
	}
}
