package AgendadorConsulta;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class AgendamentoCRUD {

	static Scanner sc = new Scanner(System.in);
	static ArrayList<Pessoas> Consulta = new ArrayList<>();
	static int opcao;


	public static boolean validarCpf(String cpf) {
		// remove caracteres nao numericos
		cpf = cpf.replaceAll("[^0-9]", "");

		// verifica se o CPF tem 11 digitos
		if (cpf.length() != 11) {
			return false;
		}

		// verifica se todos os digitos sao iguais
		boolean todosDigitosIguais = true;
		for (int i = 1; i < cpf.length(); i++) {
			if (cpf.charAt(i) != cpf.charAt(0)) {
				todosDigitosIguais = false;
				break;
			}
		}
		if (todosDigitosIguais) {
			return false;
		}

		
		try {
			
			int soma = 0;
			for (int i = 0; i < 9; i++) {
				soma += Integer.parseInt(cpf.substring(i, i + 1)) * (10 - i);
			}
			int digito1 = 11 - (soma % 11);
			if (digito1 > 9) {
				digito1 = 0;
			}

			
			soma = 0;
			for (int i = 0; i < 10; i++) {
				soma += Integer.parseInt(cpf.substring(i, i + 1)) * (11 - i);
			}
			int digito2 = 11 - (soma % 11);
			if (digito2 > 9) {
				digito2 = 0;
			}

			
			String digitosVerificadores = cpf.substring(9, 11);
			String digitosCalculados = String.valueOf(digito1) + String.valueOf(digito2);

			return digitosVerificadores.equals(digitosCalculados);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void criarPessoa() {
		try {
			System.out.println("--- Adicionar Pessoa ---");
			System.out.println("Digite o nome: ");
			String nome = sc.nextLine();

			System.out.println("Digite o CPF (apenas números): ");
			String cpf = sc.nextLine();

			System.out.println("Digite o telefone (apenas números, 9 a 11 dígitos): ");
			String telefone = sc.nextLine();

			System.out.println("Digite o email: ");
			String email = sc.nextLine();

			// validaçao de campos vazios
			if (nome.trim().isEmpty() || cpf.trim().isEmpty() || telefone.trim().isEmpty() || email.trim().isEmpty()) {
				throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
			}

			// validaçao do telefone
			if (!telefone.matches("\\d{9,11}")) {
				throw new IllegalArgumentException("O telefone deve conter apenas números, com 9 a 11 dígitos.");
			}

			// validaçao do CPF com a nova função
			if (!validarCpf(cpf)) {
				throw new IllegalArgumentException("CPF inválido. Por favor, digite um CPF válido.");
			}

			// verificaçao de CPF duplicado
			for (Pessoas p : Consulta) {
				if (p.getCpf().equals(cpf)) {
					throw new IllegalStateException("Já existe uma pessoa cadastrada com este CPF.");
				}
			}

			Consulta.add(new Pessoas(nome, cpf, email, telefone));
			System.out.println("✅ Pessoa adicionada com sucesso!");

		} catch (IllegalArgumentException | IllegalStateException e) {
			System.err.println("❌ Erro: " + e.getMessage());
		}
	}

	public static void editarPessoa() {
		try {
			System.out.println("--- Editar Pessoa ---");
			System.out.println("Digite o CPF da pessoa para editar:");
			String procurarCPF = sc.next();
			sc.nextLine();

			boolean pessoaEncontrada = false;
			for (Pessoas p1 : Consulta) {
				if (p1.getCpf().equals(procurarCPF)) {
					pessoaEncontrada = true;
					System.out.println("Pessoa encontrada: " + p1.getNome() + " | Telefone: " + p1.getTelefone());
					System.out.println("-----------------------");
					System.out.println("O que você gostaria de editar?");
					System.out.println("1 - Telefone");
					System.out.println("2 - E-mail");
					System.out.println("3 - Voltar");
					System.out.println("-----------------------");

					int opcaoEdicao = sc.nextInt();
					sc.nextLine();

					switch (opcaoEdicao) {
					case 1:
						System.out.print("Novo Telefone: ");
						String newTel = sc.nextLine();
						p1.setTelefone(newTel);
						System.out.println("✅ Telefone atualizado com sucesso!");
						break;
					case 2:
						System.out.print("Novo E-mail: ");
						String newEmail = sc.nextLine();
						p1.setEmail(newEmail);
						System.out.println("✅ E-mail atualizado com sucesso!");
						break;
					case 3:
						System.out.println("Voltando ao menu principal...");
						break;
					default:
						System.err.println("❌ Opção de edição inválida. Voltando ao menu.");
					}
					break;
				}
			}
			if (!pessoaEncontrada) {
				System.err.println("❌ Pessoa com CPF " + procurarCPF + " não encontrada.");
			}
		} catch (InputMismatchException e) {
			System.err.println("❌ Erro: Entrada inválida. Por favor, digite um número para a opção de edição.");
			sc.nextLine(); 
		}
	}

	public static void listarPessoa() {
		if (Consulta.isEmpty()) {
			System.out.println("ℹ️ Nenhuma pessoa cadastrada.");
		} else {
			System.out.println("--- Lista de Pessoas Cadastradas ---");
			for (Pessoas pessoa : Consulta) {
				pessoa.exibir();
			}
		}
	}

	public static void deletarPessoa() {
		System.out.println("--- Deletar Pessoa ---");
		System.out.println("Digite o CPF para excluir o cadastro:");
		String removerCpf = sc.next();
		sc.nextLine();

		boolean removido = false;
		Iterator<Pessoas> iterator = Consulta.iterator();
		while (iterator.hasNext()) {
			Pessoas p1 = iterator.next();
			if (p1.getCpf().equals(removerCpf)) {
				iterator.remove();
				System.out.println("✅ Pessoa com CPF " + removerCpf + " removida.");
				removido = true;
				break;
			}
		}
		if (!removido) {
			System.err.println("❌ Pessoa com CPF " + removerCpf + " não encontrada.");
		}
	}

	public static void main(String[] args) {
		do {
			System.out.println("\n--- Menu Principal ---");
			System.out.println("1 - Adicionar Pessoa");
			System.out.println("2 - Editar Pessoa");
			System.out.println("3 - Listar Pessoas");
			System.out.println("4 - Deletar Pessoa");
			System.out.println("5 - Sair");
			System.out.println("6 - Gerar Excel");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = sc.nextInt();
				sc.nextLine();

				switch (opcao) {
				case 1:
					criarPessoa();
					break;
				case 2:
					editarPessoa();
					break;
				case 3:
					listarPessoa();
					break;
				case 4:
					deletarPessoa();
					break;
				case 5:
					System.out.println("👋 Saindo do sistema...");
					break;
				case 6:
					System.out.println("Recurso de exportação para Excel ainda não implementado.");
					break;
				default:
					System.err.println("❌ Opção inválida. Por favor, escolha um número entre 1 e 6.");
				}
			} catch (InputMismatchException e) {
				System.err.println("❌ Erro: Entrada inválida. Por favor, digite um número do menu.");
				sc.nextLine();
				opcao = 0;
			}
		} while (opcao != 5);
		sc.close();
	}
}

		
	
