package main2;

public class Medicos extends Pessoas {

	String especialidade;
	String crm;
	
	public Medicos(String nome, String cpf, String telefone, String crm, String especialidade) {
		super(nome, cpf, telefone);
		this.especialidade = especialidade;
		this.crm = crm;

	}
	public String getEspecialidade() {return especialidade;}
	public void setEspecialidade(String especialidade) {this.especialidade = especialidade;}
	public String getCrm() {return crm;}	
	public void setCrm(String crm) {this.crm = crm;}
	

}
