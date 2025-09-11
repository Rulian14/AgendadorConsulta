package main2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class testeMain {
    static int opcao;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("\nBOM DIA, o que deseja?");
            System.out.println("1 - Menu de Agendamentos");
            System.out.println("2 - Menu de Médicos");
            System.out.println("3 - Sair");

            try {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1 -> menuConsultas();
                    case 2 -> menuMedicos();
                    case 3 -> System.out.println("Saindo...");
                    default -> System.err.println("Opção inválida. Digite um número entre 1 e 3.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Entrada inválida. Por favor, digite um número do menu.");
                sc.nextLine();
                opcao = 0;
            }
        } while (opcao != 3);

        sc.close();
    }

    private static void menuConsultas() {
        int opcaoConsulta = 0;
        do {
            System.out.println("\n--- Menu de Consultas ---");
            System.out.println("1 - Adicionar Consulta");
            System.out.println("2 - Editar Consulta");
            System.out.println("3 - Voltar");

            try {
                opcaoConsulta = sc.nextInt();
                sc.nextLine();

                switch (opcaoConsulta) {
                    case 1 -> Atendente.realizarAgendamento();
                    case 2 -> Atendente.editarConsulta();
                    case 3 -> System.out.println("Voltando ao menu principal...");
                    default -> System.err.println("Opção inválida. Digite um número entre 1 e 3.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Entrada inválida. Digite um número válido.");
                sc.nextLine();
                opcaoConsulta = 0;
            }

        } while (opcaoConsulta != 3);
    }

    private static void menuMedicos() {
        int opcaoMedico = 0;
        do {
            System.out.println("\n--- Menu de Médicos ---");
            System.out.println("1 - Adicionar Médico");
            System.out.println("2 - Editar Médico");
            System.out.println("3 - Listar Médicos");
            System.out.println("4 - Deletar Médico");
            System.out.println("5 - Voltar");

            try {
                opcaoMedico = sc.nextInt();
                sc.nextLine();

                switch (opcaoMedico) {
                    case 1 -> criarMedicos.criarMedico();
                    case 2 -> criarMedicos.editarMedico();
                    case 3 -> criarMedicos.listarMedico();
                    case 4 -> criarMedicos.deletarMedico();
                    case 5 -> System.out.println("Voltando ao menu principal...");
                    default -> System.err.println("Opção inválida. Digite um número entre 1 e 5.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Entrada inválida. Digite um número válido.");
                sc.nextLine();
                opcaoMedico = 0;
            }

        } while (opcaoMedico != 5);
    }
}
