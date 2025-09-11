package main2;

import java.util.InputMismatchException;
import java.util.Scanner;

import main2.AgendamentoCRUD;

public class testeMain {
	static int opcao;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		do{
		System.out.println("BOM DIA, oque deseja?");
		System.out.println("1 - Prosseguir para agendametos?");
		System.out.println("2 - Criar um Medico?");
		System.out.println("3- sair");

		try {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        Atendente.realizarAgendamento();
                        break;
                    case 2:
                        do{
                       
                            System.out.println("\n--- Menu Principal ---");
                            System.out.println("1 - Adicionar Medico");
                            System.out.println("2 - Editar Medico");
                            System.out.println("3 - Listar Medicos");
                            System.out.println("4 - Deletar Medicos");
                            System.out.println("5 - Sair");
                            try{
                                  opcao = sc.nextInt();
                                  sc.nextLine();

                                switch (opcao) {  
                                    case 1:
                                       criarMedicos.criarMedico();
                                     break;
                                    case 2:
                                        criarMedicos.editarMedico();
                                        break;
                                     case 3:
                                        criarMedicos.listarMedico();
                                        break;
                                         case 4:
                                        criarMedicos.deletarMedico();
                                        break;
                                    case 5:
                                        System.out.println("Voltando...");
                                        break;
                                    default:
                                        System.err.println("Opção inválida. Digite um número entre 1 a 5.");
                                }  

                            }catch (InputMismatchException e) {
                                System.err.println(" Erro: Entrada inválida. Por favor, digite um número do menu.");
                                sc.nextLine();
                                opcao = 0;
            }
                            

                        }while (opcao != 5);
                        break;
                    case 3:
						System.out.println("Saindo...");
                        break;
						  default:
                        System.err.println(" Opção inválida. Digite um número entre 1 e 6.");
                }
            } catch (InputMismatchException e) {
                System.err.println(" Erro: Entrada inválida. Por favor, digite um número do menu.");
                sc.nextLine();
                opcao = 0;
            }
			} while (opcao != 3);
        sc.close();
	}
}
