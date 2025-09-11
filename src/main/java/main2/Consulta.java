package main2;

import java.time.LocalDateTime;

public class Consulta {
  private LocalDateTime dataHora;
    private Pacientes paciente;  
    private Medicos medico;      
    private StatusConsulta status;

    public enum StatusConsulta {
        AGENDADA,
        REALIZADA,
        CANCELADA
    }

    public Consulta(LocalDateTime  dataHora, Pacientes paciente, Medicos medico) {
        this.dataHora = dataHora;
        this.paciente = paciente;
        this.medico = medico;
        this.status = StatusConsulta.AGENDADA;
    }

    public LocalDateTime getDataHora() {return dataHora;}

    public void setDataHora(LocalDateTime  dataHora) {this.dataHora = dataHora;}

    public Pacientes getPaciente() {return paciente;}

    public void setPaciente(Pacientes paciente) {this.paciente = paciente;}

    public Medicos getMedico() {return medico;}

    public void setMedico(Medicos medico) {this.medico = medico;}

    public StatusConsulta getStatus() { return status;}

    public void setStatus(StatusConsulta status) {this.status = status;}

}
