package main2;

public class Atendente extends Pessoas{

	String ID;
	
	public Atendente(String nome, String cpf, String telefone, String ID) {
		super(nome, cpf, telefone);
		this.ID = ID;
		
	}
	
	public void realizarAgendamento(Pacientes paciente) {
	    System.out.println("Agendamento realizado para: " + paciente.getNome());
	}
	public void exportarExcel() {
		
	}
}
