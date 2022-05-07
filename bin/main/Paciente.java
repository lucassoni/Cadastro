package src.main;
import java.io.Serializable;

public class Paciente extends Pessoa implements Serializable {
    private String endereco;
    private String email;
    private String imagem;

    public Paciente(String nome, String telefone, String endereco, String email, String imagem) {
        super(nome, telefone);
        this.endereco = endereco;
        this.email = email;
        this.imagem = imagem;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void imprimePaciente() {
        System.out.println("\nNome: ");
        System.out.println(getNome());

        System.out.println("Telefone: ");
        System.out.println(getTelefone());

        System.out.println("Endereco: ");
        System.out.println(getEndereco());

        System.out.println("Email: ");
        System.out.println(getEmail());
    }
}