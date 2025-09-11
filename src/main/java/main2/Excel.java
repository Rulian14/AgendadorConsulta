package main2;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Excel {

    public static void gerar(List<Consulta> consultas) {
        if (consultas == null || consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada!");
            return;
        }

        DateTimeFormatter formatterArquivo = DateTimeFormatter.ofPattern("dd-MM");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        Map<Medicos, List<Consulta>> consultasPorMedico = 
            consultas.stream().collect(Collectors.groupingBy(Consulta::getMedico));

        for (Map.Entry<Medicos, List<Consulta>> entry : consultasPorMedico.entrySet()) {
            Medicos medico = entry.getKey();
            List<Consulta> consultasMedico = entry.getValue();

        
            File pasta = new File("Dr " + medico.getNome());
            if (!pasta.exists()) {
                System.err.println("A pasta do médico não existe: " + pasta.getAbsolutePath());
                continue;
            }

            // Agrupar consultas por data para criar arquivos separados
            Map<String, List<Consulta>> consultasPorData = consultasMedico.stream()
                    .collect(Collectors.groupingBy(c -> c.getDataHora().format(formatterArquivo)));

            for (Map.Entry<String, List<Consulta>> dataEntry : consultasPorData.entrySet()) {
                String data = dataEntry.getKey();
                List<Consulta> consultasData = dataEntry.getValue();

                File arquivo = new File(pasta, data + ".xlsx");
                Workbook workbook;
                Sheet sheet;

                try {
                    if (arquivo.exists()) {
                        try (FileInputStream fis = new FileInputStream(arquivo)) {
                            workbook = new XSSFWorkbook(fis);
                        }
                        // Limpar todas as linhas existentes
                        sheet = workbook.getSheetAt(0);
                        int lastRow = sheet.getLastRowNum();
                        for (int i = lastRow; i >= 0; i--) {
                            Row row = sheet.getRow(i);
                            if (row != null) sheet.removeRow(row);
                        }
                    } else {
                        workbook = new XSSFWorkbook();
                        sheet = workbook.createSheet("Consultas");
                    }

                    // Cabeçalho
                    Row headerRow = sheet.createRow(0);
                    headerRow.createCell(0).setCellValue("Paciente");
                    headerRow.createCell(1).setCellValue("Horário");
                    headerRow.createCell(2).setCellValue("Status");

                    // Preencher consultas
                    int rowNum = 1;
                    for (Consulta c : consultasData) {
                        Row row = sheet.createRow(rowNum++);
                        row.createCell(0).setCellValue(c.getPaciente().getNome());
                        row.createCell(1).setCellValue(c.getDataHora().format(formatterHora));
                        row.createCell(2).setCellValue(c.getStatus().name());
                    }

                    // Salvar arquivo
                    try (FileOutputStream fileOut = new FileOutputStream(arquivo)) {
                        workbook.write(fileOut);
                    }

                    workbook.close();
                    System.out.println("Arquivo Excel atualizado em: " + arquivo.getAbsolutePath());

                } catch (IOException e) {
                    System.err.println("Erro ao criar/atualizar o arquivo para " + medico.getNome() + ": " + e.getMessage());
                }
            }
        }
    }
}
