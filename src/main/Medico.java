
public class Medico extends Pessoa {
    private String endereco;

    public Medico(String nome, String telefone, String endereco) {
        super(nome, telefone);
        this.endereco = endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void imprimeMedico() {
        System.out.println("\nNome: ");
        System.out.println(getNome());

        System.out.println("Telefone: ");
        System.out.println(getTelefone());

        System.out.println("Endereco: ");
        System.out.println(getEndereco());
        System.out.println();
    }
}