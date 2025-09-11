package main2;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Atendente {

    static Scanner sc = new Scanner(System.in);

    public static void realizarAgendamento() {

        // --- Criar ou pegar paciente ---
        Pacientes paciente = AgendamentoCRUD.criarPessoa();
        if (paciente == null) {
            System.out.println("Não foi possível criar/selecionar o paciente.");
            return;
        }
        if (criarMedicos.listaMedicos.isEmpty()) {
          System.out.println("Não há médicos cadastrados. Não é possível agendar consultas.");
            return; // sai do método
        }
        // --- Escolher médico ---
System.out.println("Médicos disponíveis:");
for (int i = 0; i < criarMedicos.listaMedicos.size(); i++) {
    Medicos m = criarMedicos.listaMedicos.get(i);
    System.out.printf("%d - %s\n", i + 1, m.getNome());
}

int opcaoMedico = -1;
while (true) {
    try {
        System.out.print("Escolha o número do médico: ");
        opcaoMedico = Integer.parseInt(sc.nextLine()) - 1; // converte para índice
        if (opcaoMedico >= 0 && opcaoMedico < criarMedicos.listaMedicos.size()) {
            break; // entrada válida
        } else {
            System.out.println("Número inválido. Tente novamente.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Digite apenas números.");
    }
}

Medicos medico = criarMedicos.listaMedicos.get(opcaoMedico);
        // --- Inserir data/hora ---
          LocalDateTime dataHora = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        while (dataHora == null) {
            try {
                System.out.print("Data e hora da consulta (dd/MM/yyyy HH:mm): ");
                String entrada = sc.nextLine();
                dataHora = LocalDateTime.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido! Use o formato dd/MM/yyyy HH:mm (ex.: 12/09/2025 14:00).");
            }
        }

        // --- Criar consulta ---
        Consulta novaConsulta = new Consulta(dataHora, paciente, medico);
        AgendamentoCRUD.listaConsultas.add(novaConsulta);

        System.out.println("Consulta agendada com sucesso para " + paciente.getNome() +
                           " com o médico " + medico.getNome());

        // --- Gerar Excel ---
        Excel.gerar(AgendamentoCRUD.listaConsultas);
    }
   
    public static void editarConsulta() {
    if (AgendamentoCRUD.listaConsultas.isEmpty()) {
        System.out.println("Nenhuma consulta cadastrada.");
        return;
    }

    System.out.println("--- Editar Consulta ---");
    for (int i = 0; i < AgendamentoCRUD.listaConsultas.size(); i++) {
        Consulta c = AgendamentoCRUD.listaConsultas.get(i);
        System.out.printf("%d - Paciente: %s | Médico: %s | Data/Hora: %s | Status: %s%n",
                i + 1,
                c.getPaciente().getNome(),
                c.getMedico().getNome(),
                c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                c.getStatus().name());
    }

    int opcao = -1;
    while (true) {
        try {
            System.out.print("Escolha o número da consulta que deseja editar: ");
            opcao = Integer.parseInt(sc.nextLine()) - 1;
            if (opcao >= 0 && opcao < AgendamentoCRUD.listaConsultas.size()) break;
            System.out.println("Número inválido. Tente novamente.");
        } catch (NumberFormatException e) {
            System.out.println("Digite apenas números.");
        }
    }

    Consulta c = AgendamentoCRUD.listaConsultas.get(opcao);

    System.out.println("O que deseja editar?");
    System.out.println("1 - Data/Hora");
    System.out.println("2 - Status");
    int escolha = Integer.parseInt(sc.nextLine());

    switch (escolha) {
        case 1:
            LocalDateTime novaDataHora = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            while (novaDataHora == null) {
                try {
                    System.out.print("Nova Data/Hora (dd/MM/yyyy HH:mm): ");
                    novaDataHora = LocalDateTime.parse(sc.nextLine(), formatter);
                } catch (Exception e) {
                    System.out.println("Formato inválido! Tente novamente.");
                }
            }
            c.setDataHora(novaDataHora);
            System.out.println("Data/Hora atualizada com sucesso!");
            break;
        case 2:
            System.out.println("Status disponível: AGENDADA, REALIZADA, CANCELADA");
            System.out.print("Novo status: ");
            c.setStatus(Consulta.StatusConsulta.valueOf(sc.nextLine().toUpperCase()));
            System.out.println("Status atualizado com sucesso!");
            break;
        default:
            System.out.println("Opção inválida!"); 
    }
     Excel.gerar(AgendamentoCRUD.listaConsultas);
}

}
