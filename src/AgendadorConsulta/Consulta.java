package AgendadorConsulta;

public class Consulta {
    private String dataHora;
    private Pacientes paciente;  
    private Medicos medico;      
    private String descricao;
    private String status;

    public Consulta(String dataHora, Pacientes paciente, Medicos medico, String descricao, String status) {
        this.dataHora = dataHora;
        this.paciente = paciente;
        this.medico = medico;
        this.descricao = descricao;
        this.status = status;
    }

    public String getDataHora() {return dataHora;}

    public void setDataHora(String dataHora) {this.dataHora = dataHora;}

    public Pacientes getPaciente() {return paciente;}

    public void setPaciente(Pacientes paciente) {this.paciente = paciente;}

    public Medicos getMedico() {return medico;}

    public void setMedico(Medicos medico) {this.medico = medico;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getStatus() { return status;}

    public void setStatus(String status) {this.status = status;}

}
