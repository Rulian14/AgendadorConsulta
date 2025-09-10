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
		System.out.println("1 - Criar Pessoa?");
		System.out.println("2 - Criar um Medico?");
		System.out.println("3- sair");

		try {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
						 do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1 - Adicionar Pessoa");
            System.out.println("2 - Editar Pessoa");
            System.out.println("3 - Listar Pessoas");
            System.out.println("4 - Deletar Pessoa");
            System.out.println("5 - Gerar Excel");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();

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
                         //Excel.gerar();
                        break;
                    case 6:
                         System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.err.println("Opção inválida. Digite um número entre 1 e 6.");
                }
            } catch (InputMismatchException e) {
                System.err.println(" Erro: Entrada inválida. Por favor, digite um número do menu.");
                sc.nextLine();
                opcao = 0;
            }
        } while (opcao != 6);
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
                                       criarMedicos.criarMedico();;
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
