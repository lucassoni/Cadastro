import java.io.Serializable;

public class InfoEme implements Serializable {
    private String info;

    public InfoEme(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void imprimeInfoEme() {
        System.out.println(getInfo());
    }

    public void deleta() {
    }
}