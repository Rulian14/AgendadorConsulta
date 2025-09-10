package main2;

public class Pessoas {
	private String nome;
	private String cpf;
	private String telefone;
	public Pessoas(String nome, String cpf, String telefone) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void exibir() {
		System.out.println("--------------------");
		System.out.println("Nome da Pessoa: " + getNome());
		System.out.println("Telefone: " + getTelefone());
		System.out.println("Cpf: " + getCpf());
		System.out.println("--------------------");
		
	}
}
