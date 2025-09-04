package main2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AgendamentoCRUD {
	
	static Scanner sc = new Scanner (System.in);
	static ArrayList<Pessoas> Consulta = new ArrayList<>();
	static int opcao;
	
	public static void criarPessoa () {
		System.out.println("Digite o nome: ");
		String nome = sc.next();
		
		System.out.println("Digite o CPF: ");
		String cpf = sc.next();
		
		System.out.println("Digite o telefone: ");
		String telefone = sc.next();

		System.out.println("Digite o email: ");
		String email = sc.next();
		
		Consulta.add(new Pessoas(nome, cpf, email, telefone));
	}
	
	public static void editarPessoa () {
		System.out.println("---Editar Pessoa---");
		System.out.println("Digite a pessoa:");
		String ProcurarCPF = sc.next();
		for(Pessoas p1 : Consulta) {
			if (p1.getCpf().equals(ProcurarCPF)) {
				System.out.println("Pessoa encontrada: " + p1.getNome() + "Telefone: " + p1.getTelefone());
				System.out.println("-----------------------");
				System.out.println("O que vc gostaria  de editar?");
				System.out.println("1 - Telefone");
				System.out.println("2 - email");
				System.out.println("3 - voltar");
				System.out.println("-----------------------");
				opcao = sc.nextInt();
				switch(opcao) {
				case 1:
					System.out.print("novo Telefone: ");
					String newTel = sc.next();
					p1.setTelefone(newTel);
					break;
				case 2:
					System.out.print("novo Email: ");
					String newEmail = sc.next();
					p1.setEmail(newEmail);
					break;
				case 3:
					break;
				
				}
				
			}
		}
	}
	
	public static void listarPessoa () {
		for(Pessoas Pessoas : Consulta) {
			Pessoas.exibir();
		}
		
	}
	
	public static void deletarPessoa () {
		System.out.println("Digite o CPF para excluir cadastro.");
		Iterator<Pessoas> iterator = Consulta.iterator();
		String RemoverCpf = sc.next();
		while(iterator.hasNext()) {
			Pessoas p1 = iterator.next();
			if (p1.getCpf().equals(RemoverCpf)) {
				iterator.remove();
				System.out.println("Conta removida.");
			}
			else {
				System.out.println("Conta não encontrada.");
			}
		}
	}
	public static void main(String[] args) {
		do {
			System.out.println("Menu");
			System.out.println("1 - Adicionar");
			System.out.println("2 - Editar Pessoa");
			System.out.println("3 - Listar Pessoas");
			System.out.println("4 - Deletar");
			System.out.println("6 - Excel");
			System.out.println("5 - Sair");
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				AgendamentoCRUD.criarPessoa();
				break;
			case 2:
				AgendamentoCRUD.editarPessoa();
				break;
			case 3:
				AgendamentoCRUD.listarPessoa();
				break;
			case 4:
				AgendamentoCRUD.deletarPessoa();
				break;
			case 5:
				System.out.println("Saindo...");
				break;
			case 6:
    			Excel.gerar(Consulta, sc);
   				break;
			default:
				System.out.println("Informe um valor válido!");
			}
		} while (opcao != 5);
	}
}
