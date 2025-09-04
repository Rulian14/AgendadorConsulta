package main2;

import java.util.ArrayList;

public class Agenda {
    private ArrayList<Consulta> consultas;

    public Agenda() {
        this.consultas = new ArrayList<>();
    }

    public void addConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void listarMedicos() {
        System.out.println("Médicos com consultas agendadas:");
        for (Consulta c : consultas) {
            System.out.println("Dr. " + c.getMedico().getNome() + " - Especialidade: " + c.getMedico().especialidade);
        }
    }
}
