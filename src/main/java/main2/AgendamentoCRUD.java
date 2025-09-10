package main2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class AgendamentoCRUD {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Pessoas> Consulta = new ArrayList<>();
    static int opcao;

    // Validação de CPF
    public static boolean validarCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

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
            if (digito1 > 9) digito1 = 0;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Integer.parseInt(cpf.substring(i, i + 1)) * (11 - i);
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) digito2 = 0;

            String digitosVerificadores = cpf.substring(9, 11);
            String digitosCalculados = String.valueOf(digito1) + String.valueOf(digito2);

            return digitosVerificadores.equals(digitosCalculados);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Criar Pessoa
    public static void criarPessoa() {
        try {
            System.out.println("--- Adicionar Pessoa ---");
            System.out.print("Digite o nome: ");
            String nome = sc.nextLine();

            System.out.print("Digite o CPF (apenas números): ");
            String cpf = sc.nextLine();

            System.out.print("Digite o telefone (apenas números, 9 a 11 dígitos): ");
            String telefone = sc.nextLine();


            if (nome.trim().isEmpty() || cpf.trim().isEmpty() || telefone.trim().isEmpty()) {
                throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
            }

            if (!telefone.matches("\\d{9,11}")) {
                throw new IllegalArgumentException("O telefone deve conter apenas números, com 9 a 11 dígitos.");
            }

            if (!validarCpf(cpf)) {
                throw new IllegalArgumentException("CPF inválido. Por favor, digite um CPF válido.");
            }

            for (Pessoas p : Consulta) {
                if (p.getCpf().equals(cpf)) {
                    throw new IllegalStateException("Já existe uma pessoa cadastrada com este CPF.");
                }
            }

            Consulta.add(new Pessoas(nome ,cpf ,telefone));
            System.out.println("Pessoa adicionada com sucesso!");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    // Editar Pessoa
    public static void editarPessoa() {
        try {
            System.out.println("--- Editar Pessoa ---");
            System.out.print("Digite o CPF da pessoa para editar: ");
            String procurarCPF = sc.nextLine();

            boolean pessoaEncontrada = false;
            for (Pessoas p1 : Consulta) {
                if (p1.getCpf().equals(procurarCPF)) {
                    pessoaEncontrada = true;
                    System.out.println("Pessoa encontrada: " + p1.getNome() + " | Telefone: " + p1.getTelefone());
                    System.out.println("-----------------------");
                    System.out.println("O que você gostaria de editar?");
                    System.out.println("1 - Telefone");
                    System.out.println("2 - Voltar");
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
                            System.out.println("Voltando ao menu principal...");
                            break;
                        default:
                            System.err.println(" Opção inválida. Voltando ao menu.");
                    }
                    break;
                }
            }
            if (!pessoaEncontrada) {
                System.err.println(" Pessoa com CPF " + procurarCPF + " não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.err.println(" Erro: Entrada inválida. Por favor, digite um número para a opção de edição.");
            sc.nextLine();
        }
    }

    // Listar Pessoas
    public static void listarPessoa() {
        if (Consulta.isEmpty()) {
            System.out.println(" Nenhuma pessoa cadastrada.");
        } else {
            System.out.println("--- Lista de Pessoas ---");
            for (Pessoas pessoa : Consulta) {
                pessoa.exibir();
            }
        }
    }

    // Deletar Pessoa
    public static void deletarPessoa() {
        System.out.println("--- Deletar Pessoa ---");
        System.out.print("Digite o CPF para excluir o cadastro: ");
        String removerCpf = sc.nextLine();

        boolean removido = false;
        Iterator<Pessoas> iterator = Consulta.iterator();
        while (iterator.hasNext()) {
            Pessoas p1 = iterator.next();
            if (p1.getCpf().equals(removerCpf)) {
                iterator.remove();
                System.out.println(" Pessoa com CPF " + removerCpf + " removida.");
                removido = true;
                break;
            }
        }
        if (!removido) {
            System.err.println(" Pessoa com CPF " + removerCpf + " não encontrada.");
        }
    }

   
}

