package main2;


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
        int opcaoMedico = -1;
while (true) {
    try {
        System.out.print("Escolha um médico: ");
        opcaoMedico = Integer.parseInt(sc.nextLine()) - 1; // lê como String e converte
        if (opcaoMedico >= 0 && opcaoMedico < criarMedicos.listaMedicos.size()) {
            break; // entrada válida
        } else {
            System.out.println("Número inváli3do. Tente novamente.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Digite apenas números.");
    }
}
Medicos medico = criarMedicos.listaMedicos.get(opcaoMedico);
        // --- Inserir data/hora ---
        System.out.print("Data e hora da consulta (dd/MM/yyyy HH:mm): ");
        String dataHora = sc.nextLine();

        // --- Inserir descrição ---
        System.out.print("Descrição da consulta: ");
        String descricao = sc.nextLine();

        // --- Status ---
        String status = "Agendada";

        // --- Criar consulta ---
        Consulta novaConsulta = new Consulta(dataHora, paciente, medico, descricao, status);
        AgendamentoCRUD.listaConsultas.add(novaConsulta);

        System.out.println("Consulta agendada com sucesso para " + paciente.getNome() +
                           " com o médico " + medico.getNome());
    }

		
	public void exportarExcel() {
		
	}
}
