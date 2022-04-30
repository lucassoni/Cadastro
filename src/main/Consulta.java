import java.util.Date;
import java.util.Calendar;
import java.io.Serializable;

public class Consulta implements Serializable {
    private Medico medico;
    private Calendar data;
    private String diagnostico = "Consulta ainda nao foi";
    private String prescricoes = "Consulta ainda nao foi";

    public Consulta(Medico medico, Calendar data) {
        this.data = data;
        this.medico = medico;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Calendar getData() {
        return this.data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getDiagnostico() {
        return this.diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricoes() {
        return this.prescricoes;
    }

    public void setPrescricoes(String prescricoes) {
        this.prescricoes = prescricoes;
    }

    public void imprimeConsulta() {

        int minuto = this.data.get(Calendar.MINUTE);

        // if (minuto < 10) {
        // System.out.println(String.format("Consulta com o Dr. %s, na data %d/%d/%d as
        // %d:%02d\n",
        // this.medico.getNome(),
        // this.data.get(Calendar.DAY_OF_MONTH), this.data.get(Calendar.MONTH) + 1,
        // this.data.get(Calendar.YEAR),
        // this.data.get(Calendar.HOUR_OF_DAY), this.data.get(Calendar.MINUTE)));
        // } else {
        // System.out.println(String.format("Consulta com o Dr. %s, na data %d/%d/%d as
        // %d:%d\n",
        // this.medico.getNome(),
        // this.data.get(Calendar.DAY_OF_MONTH), this.data.get(Calendar.MONTH) + 1,
        // this.data.get(Calendar.YEAR),
        // this.data.get(Calendar.HOUR_OF_DAY), this.data.get(Calendar.MINUTE)));
        // }

        if (minuto < 10) {
            System.out.println(
                    String.format(
                            "Consulta com o Dr. %s, na data %d/%d/%d as %d:%02d\nDiagnostico: %s\nPrescricao: %s\n",
                            this.medico.getNome(),
                            this.data.get(Calendar.DAY_OF_MONTH), this.data.get(Calendar.MONTH) + 1,
                            this.data.get(Calendar.YEAR),
                            this.data.get(Calendar.HOUR_OF_DAY), this.data.get(Calendar.MINUTE),
                            this.diagnostico, this.prescricoes));
        } else {
            System.out.println(
                    String.format("Consulta com o Dr. %s, na data %d/%d/%d as %d:%d\nDiagnostico: %s\nPrescricao: %s\n",
                            this.medico.getNome(),
                            this.data.get(Calendar.DAY_OF_MONTH), this.data.get(Calendar.MONTH) + 1,
                            this.data.get(Calendar.YEAR),
                            this.data.get(Calendar.HOUR_OF_DAY), this.data.get(Calendar.MINUTE),
                            this.diagnostico, this.prescricoes));
        }
    }

}
