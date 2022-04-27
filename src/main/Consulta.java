import java.util.Date;

public class Consulta {
    private Medico medico;
    private Date data;
    private String diagnostico;
    private String prescricoes;

    public Consulta(Medico medico, Date data, String diagnostico, String prescricoes) {
        this.medico = medico;
        this.data = data;
        this.diagnostico = diagnostico;
        this.prescricoes = prescricoes;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(String prescricoes) {
        this.prescricoes = prescricoes;
    }

}
