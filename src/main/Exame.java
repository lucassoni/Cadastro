import java.util.Date;
import java.util.Calendar;
import java.io.Serializable;

public class Exame implements Serializable {
    private Calendar data;
    private String nome;
    private String resultado = "O resultado ainda não foi cadastrado";
    private String imagem = null;
    private String video = null;

    public Exame(String nome, Calendar data) {
        this.nome = nome;
        this.data = data;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void imprimeExame(){
        int minuto = this.data.get(Calendar.MINUTE);

        if (minuto < 10) {
            System.out.println(
                    String.format(
                            "Exame %s, marcado na data %d/%d/%d as %d:%02d\nResultado: %s\n",
                            this.nome,
                            this.data.get(Calendar.DAY_OF_MONTH), this.data.get(Calendar.MONTH) + 1,
                            this.data.get(Calendar.YEAR),
                            this.data.get(Calendar.HOUR_OF_DAY), this.data.get(Calendar.MINUTE),
                            this.resultado));
        } else {
            System.out.println(
                    String.format("Exame %s, marcado na data %d/%d/%d as %d:%d\nResultado: %s\n",
                            this.nome,
                            this.data.get(Calendar.DAY_OF_MONTH), this.data.get(Calendar.MONTH) + 1,
                            this.data.get(Calendar.YEAR),
                            this.data.get(Calendar.HOUR_OF_DAY), this.data.get(Calendar.MINUTE),
                            this.resultado));
        }
    }

    public void mostraImagem(){
        if(this.imagem == null){
            System.out.println("Não há imagem para este exame");
            return;
        }

        try {
            Listagem.DisplayImage(this.imagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostraVideo(){
        if(this.video == null){
            System.out.println("Não há video para este exame");
            return;
        }

        try {
            Listagem.DisplayVideo(this.video);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
