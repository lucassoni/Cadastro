import java.util.Date;

public class Exame {
    private Date data;
    private String resultado;
    private String[] imagens;
    private String[] videos;

    public Exame(Date data, String resultado, String[] imagens, String[] videos) {
        this.data = data;
        this.resultado = resultado;
        this.imagens = imagens;
        this.videos = videos;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String[] getImagens() {
        return imagens;
    }

    public void setImagens(String[] imagens) {
        this.imagens = imagens;
    }

    public String[] getVideos() {
        return videos;
    }

    public void setVideos(String[] videos) {
        this.videos = videos;
    }
    
}
