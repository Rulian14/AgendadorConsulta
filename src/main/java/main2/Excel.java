package main2;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Excel {

    public static void gerar(List<Pessoas> listaPessoas, Scanner teclado) {
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

        File arquivo = new File(pasta, nomeArquivo + ".xlsx");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Pessoas");

            // Cabeçalho
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Nome");
            headerRow.createCell(1).setCellValue("CPF");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Telefone");

            // Dados
            int rowNum = 1;
            for (Pessoas p : listaPessoas) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(p.getNome());
                row.createCell(1).setCellValue(p.getCpf());
                row.createCell(2).setCellValue(p.getEmail());
                row.createCell(3).setCellValue(p.getTelefone());
            }

            // Salvar o arquivo
            try (FileOutputStream fileOut = new FileOutputStream(arquivo)) {
                workbook.write(fileOut);
            }

            System.out.println("Arquivo Excel criado com sucesso em: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }
}
