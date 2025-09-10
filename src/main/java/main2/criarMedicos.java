package main2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class criarMedicos {
	static ArrayList<Medicos> listaMedicos = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);		
    //CRiar Medico
	public static void criarMedico() {
        try {
            System.out.println("--- Adicionar Medico ---");
            System.out.print("Digite o nome: ");
            String nome = sc.nextLine();

            System.out.print("Digite o CPF (apenas números): ");
            String cpf = sc.nextLine();

            System.out.print("Digite o telefone (apenas números, 9 a 11 dígitos): ");
            String telefone = sc.nextLine();

            System.out.print("Digite o CRM: ");
            String CRM = sc.nextLine();

            System.out.println("Digite a especialidade: ");
            String especialidade = sc.nextLine();


            if (nome.trim().isEmpty() || cpf.trim().isEmpty() || telefone.trim().isEmpty() || CRM.trim().isEmpty() || especialidade.trim().isEmpty()) {
                throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
            }

            if (!telefone.matches("\\d{9,11}")) {
                throw new IllegalArgumentException("O telefone deve conter apenas números, com 9 a 11 dígitos.");
            }

            if (!AgendamentoCRUD.validarCpf(cpf)) {
                throw new IllegalArgumentException("CPF inválido. Por favor, digite um CPF válido.");
            }

            for (Medicos t : listaMedicos) {
                if (t.getCpf().equals(cpf)) {
                    throw new IllegalStateException("Já existe uma Medico cadastrada com este CPF.");
                }
            }

            listaMedicos.add(new Medicos(nome, cpf, telefone, CRM, especialidade));
            System.out.println("Medico adicionada com sucesso!");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    // Editar Medico
    public static void editarMedico() {
        try {
            System.out.println("--- Editar Medico ---");
            System.out.print("Digite o CPF da Medico para editar: ");
            String procurarCPF = sc.nextLine();

            boolean medicoEncontrada = false;
            for (Medicos p1 : listaMedicos) {
                if (p1.getCpf().equals(procurarCPF)) {
                    medicoEncontrada = true;
                    System.out.println("Medico encontrada: " + p1.getNome() + " | Telefone: " + p1.getTelefone());
                    System.out.println("-----------------------");
                    System.out.println("O que você gostaria de editar?");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Telefone");
                    System.out.println("3 - Voltar");
                    System.out.println("-----------------------");

                    int opcaoEdicao = sc.nextInt();
                    sc.nextLine();

                    switch (opcaoEdicao) {
                        
                        case 1:
                            System.out.print("Novo Nome ");
                            String newNome = sc.nextLine();
                            p1.setNome(newNome);
                            System.out.println("Nome atualizado com sucesso!");
                            break;
                        case 2:
                            System.out.print("Novo Telefone: ");
                            String newTel = sc.nextLine();
                            p1.setTelefone(newTel);
                            System.out.println("Telefone atualizado com sucesso!");
                            break;
                        case 3:
                            System.out.println("Voltando ao menu principal...");
                            break;
                        default:
                            System.err.println(" Opção inválida. Voltando ao menu.");
                    }
                    break;
                }
            }
            if (!medicoEncontrada) {
                System.err.println(" Medico com CPF " + procurarCPF + " não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.err.println(" Erro: Entrada inválida. Por favor, digite um número para a opção de edição.");
            sc.nextLine();
        }
    }
    // Listar Medico
    public static void listarMedico() {
        if (listaMedicos.isEmpty()) {
            System.out.println(" Nenhuma Medico cadastrada.");
        } else {
            System.out.println("--- Lista de Medico ---");
            for (Medicos m : listaMedicos) {
                m.exibir();
            }
        }
    }
    // Deletar Medico
    public static void deletarMedico() {
        System.out.println("--- Deletar Medico ---");
        System.out.print("Digite o CPF para excluir o cadastro: ");
        String removerCpf = sc.nextLine();

        boolean removido = false;
        Iterator<Medicos> iterator = listaMedicos.iterator();
        while (iterator.hasNext()) {
            Medicos p1 = iterator.next();
            if (p1.getCpf().equals(removerCpf)) {
                iterator.remove();
                System.out.println(" Medico com CPF " + removerCpf + " removida.");
                removido = true;
                break;
            }
        }
        if (!removido) {
            System.err.println(" Medico com CPF " + removerCpf + " não encontrada.");
        }
    }
}
