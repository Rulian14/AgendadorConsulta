package AgendadorConsulta;

import java.io.*;
import java.util.*;

public class Excel {
    public static void gerar(List<Pessoas> listaPessoas) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Digite o nome da pasta: ");
        String nomePasta = teclado.next();

        File pasta = new File(nomePasta);
        if (pasta.mkdir()) {
            System.out.println("Pasta criada com sucesso!");
        } else {
            System.out.println("A pasta já existe ou não pode ser criada");
        }

        System.out.println("Digite o nome do arquivo (sem extensão): ");
        String nomeArquivo = teclado.next();

        File arquivo = new File(pasta, nomeArquivo + ".csv");

        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            writer.println("Nome,CPF,Email,Telefone");

            for (Pessoas p : listaPessoas) {
                writer.println(p.getNome() + "," + p.getCpf() + "," + p.getEmail() + "," + p.getTelefone());
            }

            System.out.println("Arquivo CSV criado com sucesso em: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }
}
