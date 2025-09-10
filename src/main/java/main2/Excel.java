package main2;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Excel {

    public static void gerar(List<Consulta> consultas) {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada!");
            return;
        }

        // Pegar o médico da primeira consulta (assumindo que todas são do mesmo médico)
        String nomeMedico = consultas.get(0).getMedico().getNome();

        // Criar pasta do médico
        File pasta = new File(nomeMedico);
        if (!pasta.exists()) {
            pasta.mkdir();
            System.out.println("Pasta criada para o médico: " + nomeMedico);
        }

        // Pegar a data da primeira consulta (formato dia-mês)
        DateTimeFormatter formatterEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatterArquivo = DateTimeFormatter.ofPattern("dd-MM");

        LocalDateTime dataHora = LocalDateTime.parse(consultas.get(0).getDataHora(), formatterEntrada);
        String nomeArquivo = formatterArquivo.format(dataHora) + ".xlsx";

        File arquivo = new File(pasta, nomeArquivo);

        Workbook workbook;
        Sheet sheet;

        try {
            // Se já existe, abre, senão cria
            if (arquivo.exists()) {
                FileInputStream fis = new FileInputStream(arquivo);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Consultas");

                // Cabeçalho
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Paciente");
                headerRow.createCell(1).setCellValue("Horário");
                headerRow.createCell(2).setCellValue("Status");
            }

            // Descobrir última linha preenchida
            int rowNum = sheet.getLastRowNum() + 1;

            // Preencher com as consultas
            for (Consulta c : consultas) {
                LocalDateTime dh = LocalDateTime.parse(c.getDataHora(), formatterEntrada);
                String hora = dh.format(DateTimeFormatter.ofPattern("HH:mm"));

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(c.getPaciente().getNome());
                row.createCell(1).setCellValue(hora);
                row.createCell(2).setCellValue(c.getStatus());
            }

            // Salvar arquivo
            try (FileOutputStream fileOut = new FileOutputStream(arquivo)) {
                workbook.write(fileOut);
            }

            workbook.close();
            System.out.println("Arquivo Excel atualizado em: " + arquivo.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Erro ao criar/atualizar o arquivo: " + e.getMessage());
        }
    }
}
